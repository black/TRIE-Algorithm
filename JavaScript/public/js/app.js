let items = [];

function readTextFile(file) {
     var rawFile = new XMLHttpRequest();
     rawFile.open("GET", file, false);
     rawFile.onreadystatechange = function () {
          if (rawFile.readyState === 4) {
               if (rawFile.status === 200 || rawFile.status == 0) {
                    var allText = rawFile.responseText;
                    items = allText.split("\n");
               }
          }
     }
     rawFile.send(null);
}

readTextFile('./js/english.txt');


const root = new Node('\0');
let io_box = document.getElementById('strs');
let lst = document.getElementById('word-list');

for (let item in items) {
     add(items[item], 0, root);
}

function updateSuggestion(e) {
     let str = e.target.value;
     let prediction = search(str, 0, root);
     lst.innerHTML = "";
     for (let word in prediction) {
          lst.innerHTML += '<li onclick="handleClick(this)">' + prediction[word] + '</li>';
     }
}

function handleClick(e) {
     const text = e.innerHTML;
     io_box.value = text;
}

io_box.addEventListener("keyup", updateSuggestion);