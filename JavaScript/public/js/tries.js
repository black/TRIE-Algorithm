// create note
function Node(ch) {
     this.ch = ch; // current string
     this.terminal = false; // if terminal node then TRUE or FALSe
     this.branch = {};
     this.words = [];
}

// add new node upon check
// recursive method to add node for each word.
let addNode = (node, i, word) => {
     if (i === word.length) {
          node.terminal = true;
          return;
     }

     let ch = word[i];
     if (!node.branch[ch]) {
          node.branch[ch] = new Node(ch);
     }

     node.words.push(word);
     addNode(node.branch[ch], i + 1, word);
};

// search node by charater
let search = (node, i, word) => {
     if (i === word.length) {
          return node.words;
     }

     let ch = word[i];
     if (!node.branch[ch]) {
          return [];
     }
     return search(node.branch[ch], i + 1, word);
};