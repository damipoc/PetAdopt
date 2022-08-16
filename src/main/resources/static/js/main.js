"use strict";

// Selectors

//Divs
let resultsDiv = document.querySelector("#results-div");

//Inputs
let typeInput = document.querySelector("#ageInput");
let nameInput = document.querySelector("#nameInput");
let ageInput = document.querySelector("#ageInput");
let notesInput = document.querySelector("#notesInput");

//Button
let createBtn = document.querySelector("#createBtn");


// Functions
let printResults = (result) => {
    let entryDiv = document.createElement("div");
    entryDiv.setAttribute("class", "entry-div")
    entryDiv.textContent = `${result.id} | ${result.type} | ${result.name} | ${result.age} | ${result.notes}`

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

    console.log(obj);
    axios.post("http://localhost:8080/animal/create", obj)
        .then((res) => {
            getAll();
        }).catch((err => { console.log(err); }));



}


// Listeners
createBtn.addEventListener("click", create)