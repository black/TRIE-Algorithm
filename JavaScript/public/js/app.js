let items = [];
let max_word = 10;

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

// create parent root node
let root = new Node("\0");
// add all words to the tree
for (let word of items) {
     addNode(root, 0, word);
}

// Interface ---------------- 
let tv = $("#text-box");
let lst = $("#menu");

tv.on("keyup", () => {
     let prediction = search(root, 0, tv.val(), 10);
     addSuggestions(prediction);
});

// populating suggestions to ul li list
function addSuggestions(words) {
     lst.empty();
     for (let word of words) { // take top 10 words
          lst.append('<li onclick="liSelector(event)" class="list-item" >' + word + '</li>');
     }
}

function liSelector(e) {
     let text = $(e.target).text();
     tv.val(text);
}