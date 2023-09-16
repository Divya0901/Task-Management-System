class Task {
  constructor(id, title, description, createDate, endDate, status) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.createDate = createDate;
    this.endDate = endDate;
    this.status = status;
  }
}

class TaskDto {
  constructor(taskTitle, taskDescription) {
    this.taskTitle = taskTitle;
    this.taskDescription = taskDescription;
  }
}

class TaskResponseDto {
  constructor(status, task) {
    this.status = status;
    this.task = task;
  }
}

// Selectors
const createTaskTitle = document.querySelector(".create-task-title");
const createTaskDescription = document.querySelector(".create-task-description");
const createTaskButton = document.querySelector(".create-task-button");
const taskList = document.querySelector(".task-list");
const filterOption = document.querySelector(".filter-task");

// Event Listeners
document.addEventListener("DOMContentLoaded", getAllTaskList);
createTaskButton.addEventListener("click", addTask);
taskList.addEventListener("click", deleteCheck);
filterOption.addEventListener("click", filterTask);

// Functions
function addTask(event) {
  // prevent form from submitting
  event.preventDefault();

  // Define the API endpoint
  const apiUrl = 'http://localhost:8080/saveTaskDetails';

  // Create a new TaskDto instance with data
  const taskData = new TaskDto(createTaskTitle.value, createTaskDescription.value);

  // Configure the fetch request
  const requestOptions = {
    method: 'POST', // or 'PUT', 'GET', etc. depending on your API
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(taskData),
  };

  // Make the API request
  fetch(apiUrl, requestOptions)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      // Handle the API response data
      // Parse the response into a TaskResponseDto
      if (data.status == "Task Created Successfully") {
        const taskData = new Task(
          data.task.id,
          data.task.title,
          data.task.description,
          new Date(data.task.assignedDate),
          data.task.endDate ? new Date(data.task.endDate) : null,
          data.task.statusOfTask
        );

        const taskResponse = new TaskResponseDto(data.status, taskData);
        console.log('Task Response:', taskResponse);
        // create DIV
        const taskDiv = document.createElement("div");
        taskDiv.classList.add("task");

        // create li
        const newTaskId = document.createElement("li");
        newTaskId.innerText = data.task.id;
        newTaskId.classList.add("task-item");
        taskDiv.appendChild(newTaskId);

        const newTaskTitle = document.createElement("li");
        newTaskTitle.innerText = data.task.title;
        newTaskTitle.classList.add("task-item");
        taskDiv.appendChild(newTaskTitle);

        const newTaskDescription = document.createElement("li");
        newTaskDescription.innerText = data.task.description;
        newTaskDescription.classList.add("task-item");
        taskDiv.appendChild(newTaskDescription);

        const newTaskCreateDate = document.createElement("li");
        newTaskCreateDate.innerText = new Date(data.task.createDate);
        newTaskCreateDate.classList.add("task-item");
        taskDiv.appendChild(newTaskCreateDate);

        // check mark button
        const completedButton = document.createElement("button");
        completedButton.classList.add("complete-btn");
        completedButton.innerHTML = '<i class="fas fa-check"> </i>';
        taskDiv.appendChild(completedButton);

        // check trash button
        const trashButton = document.createElement("button");
        trashButton.classList.add("trash-btn");
        trashButton.innerHTML = '<i class="fas fa-trash"> </i>';
        taskDiv.appendChild(trashButton);

        // append to list
        taskList.appendChild(taskDiv);

        // clear Task input values
        createTaskTitle.value = "";
        createTaskDescription.value = "";
      }
      console.log('API Response:', data);
    })
    .catch((error) => {
      console.error('API Error:', error);
    });
}

function deleteCheck(e) {
  const item = e.target;
  const task = item.parentElement;

  // Delete task
  if (item.classList[0] === "trash-btn") {
    // console.log('item', item);
    // console.log('task', task.childNodes[0].innerText);

    // Animation
    task.classList.add("fall");
    removeTasks(task.childNodes[0].innerText);
    task.addEventListener("transitionend", function () {
      task.remove();
    });
  }

  // check mark
  if (item.classList[0] === "complete-btn") {
    const task = item.parentElement;
    task.classList.toggle("completed");
    completedTasks(task.childNodes[0].innerText);
  }
}

function filterTask(e) {
  const tasks = taskList.childNodes;

  tasks.forEach(function (task) {
    switch (e.target.value) {
      case "all":
        task.style.display = "flex";
        break;

      case "completed":
        if (task.classList.contains("completed")) {
          task.style.display = "flex";
        } else {
          task.style.display = "none";
        }
        break;

      case "uncompleted":
        if (!task.classList.contains("completed")) {
          task.style.display = "flex";
        } else {
          task.style.display = "none";
        }
        break;
    }
  });
}

function getAllTaskList() {
  let tasks = []

  const apiUrl = 'http://localhost:8080/getAllTasksDetails';

  // Make a GET request using the Fetch API
  fetch(apiUrl)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      // Handle the response data here (data contains the task)
      // Convert the API response into a list of Task objects
      tasks = data.map((item) => {
        return new Task(
          item.id,
          item.title,
          item.description,
          new Date(item.assignedDate),
          item.endDate ? new Date(item.endDate) : null,
          item.statusOfTask
        );
      });
      // console.log("tasks: ", tasks);
      // console.log('Task Data:', data);
      tasks.forEach(function (task) {
        // create DIV
        const taskDiv = document.createElement("div");
        taskDiv.classList.add("task");

        // create li
        const newTaskId = document.createElement("li");
        newTaskId.innerText = task.id;
        // console.log('taskTitle: ', task.title)
        newTaskId.classList.add("task-item");
        taskDiv.appendChild(newTaskId);

        const newTaskTitle = document.createElement("li");
        newTaskTitle.innerText = task.title;
        // console.log('taskTitle: ', task.title)
        newTaskTitle.classList.add("task-item");
        taskDiv.appendChild(newTaskTitle);

        const newTaskDescription = document.createElement("li");
        newTaskDescription.innerText = task.description;
        newTaskDescription.classList.add("task-item");
        taskDiv.appendChild(newTaskDescription);

        const newTaskCreateDate = document.createElement("li");
        newTaskCreateDate.innerText = task.createDate;
        newTaskCreateDate.classList.add("task-item");
        taskDiv.appendChild(newTaskCreateDate);

//        console.log('status: ', task.status)
        if (task.status === 'COMPLETED') {
          const newTaskEndDate = document.createElement("li");
          newTaskEndDate.innerText = task.endDate;
          newTaskEndDate.classList.add("task-item");
          taskDiv.appendChild(newTaskEndDate);
          taskDiv.classList.toggle("completed");
        }

        // check mark button
        const completedButton = document.createElement("button");
        completedButton.classList.add("complete-btn");
        completedButton.innerHTML = '<i class="fas fa-check"> </i>';
        taskDiv.appendChild(completedButton);

        // check trash button
        const trashButton = document.createElement("button");
        trashButton.classList.add("trash-btn");
        trashButton.innerHTML = '<i class="fas fa-trash"> </i>';
        taskDiv.appendChild(trashButton);

        // append to list
        taskList.appendChild(taskDiv);
      });
    })
    .catch((error) => {
      console.error('API Error:', error);
    });
}

function removeTasks(id) {
  // check already is there
  const apiUrl = 'http://localhost:8080/deleteTaskById/' + id;

  // Configure the fetch request
  const requestOptions = {
    method: 'DELETE', // or 'PUT', 'GET', etc. depending on your API
  };

  // Make the API request
  fetch(apiUrl, requestOptions)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response;
    })
    .then((data) => {
      // Handle the API response data
      // Parse the response into a TaskResponseDto
      console.log(data);
    })
    .catch((error) => {
      console.error('API Error:', error);
    });
}

function completedTasks(id) {
  // check already is there
  const apiUrl = 'http://localhost:8080/completedTask/' + id;

  // Configure the fetch request
  const requestOptions = {
    method: 'PATCH', // or 'PUT', 'GET', etc. depending on your API
  };

  // Make the API request
  fetch(apiUrl, requestOptions)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response;
    })
    .then((data) => {
      // Handle the API response data
      // Parse the response into a TaskResponseDto
      console.log(data);
    })
    .catch((error) => {
      console.error('API Error:', error);
    });
}

// !localStorage.clear();
