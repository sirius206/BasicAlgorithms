//Insertion: Time O(m), Space O(m),  m is the key length
//Search: Time O(m), Space O(1)
//IsPrefix: Time O(m), Space O(1)


/*
With my solution I took the simple approach of giving each TrieNode a 26 element array of each possible child node it may have. 
I only gave 26 children nodes because we are only working with lowercase 'a' - 'z'. If you are uncertain why I made the root of my Trie 
an empty character this is a standard/typical approach for building out a Trie it is somewhat arbitrary what the root node is.

For insert I used the following algorithm. Loop through each character in the word being inserted check if the character is a 
child node of the current TrieNode i.e. check if the array has a populated value in the index of this character. 
If the current character ISN'T a child node of my current node add this character representation to the corresponding index location 
then set current node equal to the child that was added. However if the current character IS a child of the current node only 
set current node equal to the child. After evaluating the entire String the Node we left off on is marked as a word this allows 
our Trie to know which words exist in our "dictionary"

For search I simply navigate through the Trie if I discover the current character isn't in the Trie I return false.
After checking each Char in the String I check to see if the Node I left off on was marked as a word returning the result.

Starts with is identical to search except it doesn't matter if the Node I left off was marked as a word or not if entire string evaluated 
i always return true;

*/
class TrieNode {
    public char val;
    public boolean isEnd;
    public TrieNode[] children;
    public TrieNode(char c){
        this.val = c;
        this.children = new TrieNode[26];
    }
}
class Trie {
    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode(' ');
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null){
                node.children[c - 'a'] = new TrieNode(c);
            }
            node = node.children[c - 'a'];
        }
        node.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null) return false;
            node = node.children[c - 'a'];
        }
        return node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if (node.children[c - 'a'] == null) return false;
            node = node.children[c - 'a'];
        }
        return true;        
    }
}


// answer, more class functions
class TrieNode {

    // R links to node children
    private TrieNode[] links;
    private final int R = 26;
    private boolean isEnd;
    
    public TrieNode() {
        links = new TrieNode[R];
    }

    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }
    
    public TrieNode get(char ch) {
        return links[ch -'a'];
    }
    
    public void put(char ch, TrieNode node) {
        links[ch -'a'] = node;
    }
    
    public void setEnd() {
        isEnd = true;
    }
    
    public boolean isEnd() {
        return isEnd;
    }
}

class Trie {
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }
    
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
           char curLetter = word.charAt(i);
           if (node.containsKey(curLetter)) {
               node = node.get(curLetter);
           } else {
               return null;
           }
        }
        return node;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
       TrieNode node = searchPrefix(word);
       return node != null && node.isEnd();
    }
    
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}


