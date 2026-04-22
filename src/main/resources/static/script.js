const BASE_URL = "http://localhost:8080";

/* MATRIX EFFECT */
const canvas = document.getElementById("matrix");
const ctx = canvas.getContext("2d");

canvas.height = window.innerHeight;
canvas.width = window.innerWidth;

let letters = "01".split("");
let fontSize = 14;
let columns = canvas.width / fontSize;

/* OUTPUT FUNCTION */
function print(text) {
    let output = document.getElementById("output");
    output.innerText += "\n> " + text;
    output.scrollTop = output.scrollHeight;
}

/* COMMAND HANDLER */
async function runCommand(cmd) {

    let name = document.getElementById("name").value;
    let size = document.getElementById("size").value;

    print("Executing " + cmd + "...");

    let response;

    switch (cmd) {

        case "CREATE":
            response = await fetch(`${BASE_URL}/create?name=${name}&size=${size}`, { method: "POST" });
            print(await response.text());
            break;

        case "DELETE":
            response = await fetch(`${BASE_URL}/delete?name=${name}`, { method: "POST" });
            print(await response.text());
            break;

        case "READ":
            response = await fetch(`${BASE_URL}/read?name=${name}`);
            print(await response.text());
            break;

        case "CRASH":
            response = await fetch(`${BASE_URL}/crash`, { method: "POST" });
            print(await response.text());
            break;

        case "RECOVER":
            response = await fetch(`${BASE_URL}/recover`);
            print(await response.text());
            break;

        case "OPTIMIZE":
            response = await fetch(`${BASE_URL}/optimize`);
            print(await response.text());
            break;

        case "LIST":
            response = await fetch(`${BASE_URL}/files`);
            let data = await response.json();
            print(JSON.stringify(data, null, 2));
            break;
    }
}