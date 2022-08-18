"use strict";

// Selectors

//Divs
let resultsDiv = document.querySelector("#results-div");

//Forms
let formPopup = document.querySelector("#formPopup");

//Inputs
let typeInput = document.querySelector("#typeInput");
let nameInput = document.querySelector("#nameInput");
let ageInput = document.querySelector("#ageInput");
let notesInput = document.querySelector("#notesInput");

let typeUpdate = document.querySelector("#typeUpdate");
let nameUpdate = document.querySelector("#nameUpdate");
let ageUpdate = document.querySelector("#ageUpdate");
let notesUpdate = document.querySelector("#notesUpdate");


//Button
let createBtn = document.querySelector("#createBtn");

// Functions
let printResults = (result) => {
    let entryDiv = document.createElement("div");
    let updateBtn = document.createElement("button");
    let deleteBtn = document.createElement("button");
    let buttonDiv = document.createElement("div");
    let info = document.createElement("div");

    info.setAttribute("id", "divInfo");
    entryDiv.setAttribute("class", "entry-div");
    buttonDiv.setAttribute("id", "button-div");

    updateBtn.setAttribute("class", "btn btn-warning btn-sm");
    updateBtn.setAttribute("id", "updateBtn");
    updateBtn.setAttribute("onClick", `formUpdate(${result.id})`);


    deleteBtn.setAttribute("class", "btn btn-danger btn-sm");
    deleteBtn.setAttribute("id", "deleteBtn");
    deleteBtn.setAttribute("onClick", `deleteEntry(${result.id})`);

    info.textContent = `${result.id} | ${result.type} | ${result.name} | ${result.age} | ${result.notes}`;
    updateBtn.textContent = "Update";
    deleteBtn.textContent = "Delete";

    buttonDiv.appendChild(updateBtn);
    buttonDiv.appendChild(deleteBtn);
    entryDiv.appendChild(info);
    entryDiv.appendChild(buttonDiv);
    resultsDiv.appendChild(entryDiv);
}

let getAll = () => {
    axios.get("http://localhost:8080/animal/getAll")
        .then((res) => {
            resultsDiv.innerHTML = "";

            let results = res.data;

            for (let result of results) {
                printResults(result);
            }
        }).catch((err) => { console.log(err); });
}


let create = () => {

    let obj = {
        "type": typeInput.value,
        "name": nameInput.value,
        "age": ageInput.value,
        "notes": notesInput.value
    }

    axios.post("http://localhost:8080/animal/create", obj)
        .then((res) => {
            getAll();
        }).catch((err => { console.log(err); }));

}

let formUpdate = (id) => {
    div_show();

    let confirmBtn = document.createElement("button");

    confirmBtn.setAttribute("class", "btn btn-warning btn-sm");
    confirmBtn.setAttribute("id", "temp");
    confirmBtn.setAttribute("onClick", `confirmUpdate(${id})`);
    formUpdatePopulate(id);

    confirmBtn.textContent = "Confirm";
    formPopup.appendChild(confirmBtn);

}

let formUpdatePopulate = (id) => {
    axios.get(`http://localhost:8080/animal/getByID/${id}`)
        .then((res) => {
            let results = res.data;

            typeUpdate.value = results.type;
            nameUpdate.value = results.name;
            ageUpdate.value = results.age;
            notesUpdate.value = results.notes;


        }).catch((err) => { console.log(err); });
}

let confirmUpdate = (id) => {


    let obj = {
        "type": typeUpdate.value,
        "name": nameUpdate.value,
        "age": ageUpdate.value,
        "notes": notesUpdate.value
    }


    axios.put(`http://localhost:8080/animal/update/${id}`, obj)
        .then((resp) => {
            getAll();
        }).catch((err) => { console.error(err); });

    div_hide();

}

let deleteEntry = (id) => {

    axios.delete(`http://localhost:8080/animal/delete/${id}`)
        .then((res) => {
            getAll();
        }).catch((err => { console.log(err); }));
}

let div_show = () => {
    document.getElementById('abc').style.display = "block";
}

let div_hide = () => {
    formPopup.removeChild(document.getElementById("temp"));
    document.getElementById('abc').style.display = "none";
}


// Listeners
createBtn.addEventListener("click", create);