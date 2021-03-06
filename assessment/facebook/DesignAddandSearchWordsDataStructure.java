package com.leetcode;

class Solution {

    public static void main(String[] args) {
        Solution solution  = new Solution();
        int answer = solution.solve();
        System.out.println(answer);
    }

    public int solve() {

        WordDictionary wordDictionary = new WordDictionary();
        //======
        //1) case

//        wordDictionary.addWord("bad");
//        wordDictionary.addWord("dad");
//        wordDictionary.addWord("mad");
//        System.out.println(wordDictionary.search("pad")); // return False
//        System.out.println(wordDictionary.search("bad")); // return True
//        System.out.println(wordDictionary.search(".ad")); // return True
//        System.out.println(wordDictionary.search("b..")); // return True
//

        //=======
        //2) case

//        ["addWord","addWord","addWord","addWord",
//         "search","search",
//         "addWord",
//         "search","search","search","search","search","search"]
//
//        [["at"],["and"],["an"],["add"],
//         ["a"], [".at"],
//         ["bat"],
//         [".at"],["an."],["a.d."],["b."],["a.d"],["."]]
//        wordDictionary.addWord("at");
//        wordDictionary.addWord("and");
//        wordDictionary.addWord("an");
//        wordDictionary.addWord("add");
//
//        System.out.println(wordDictionary.search("a")); //false
//        System.out.println(wordDictionary.search(".at")); //false
//
//        wordDictionary.addWord("bat");
//
//        System.out.println(wordDictionary.search(".at")); //true
//        System.out.println(wordDictionary.search("an.")); //true
//        System.out.println(wordDictionary.search("a.d.")); //false
//        System.out.println(wordDictionary.search("b."));  //false
//        System.out.println(wordDictionary.search("a.d")); //true
//        System.out.println(wordDictionary.search(".")); //false
//
//        false,false,null,true,true,false,false,true,false]

        // =======
        // 3) case

//        ["WordDictionary",
//         "addWord","addWord","addWord","addWord","addWord","addWord","addWord","addWord",
//         "search","search","search","search","search","search","search","search","search","search"]
//        [[],
//         ["ran"],["rune"],["runner"],["runs"],["add"],["adds"],["adder"],["addee"],
//         ["r.n"],["ru.n.e"],["add"],["add."],["adde."],[".an."],["...s"],["....e."],["......."],["..n.r"]]

        wordDictionary.addWord("ran");
        wordDictionary.addWord("rune");
        wordDictionary.addWord("runner");
        wordDictionary.addWord("runs");
        wordDictionary.addWord("add");
        wordDictionary.addWord("adds");
        wordDictionary.addWord("adder");
        wordDictionary.addWord("addee");

        System.out.println(wordDictionary.search("r.n")); // true
        System.out.println(wordDictionary.search("ru.n.e")); // false
        System.out.println(wordDictionary.search("add")); //true
        System.out.println(wordDictionary.search("add."));//true
        System.out.println(wordDictionary.search("adde."));
        System.out.println(wordDictionary.search(".an."));
        System.out.println(wordDictionary.search("...s"));
        System.out.println(wordDictionary.search("....e."));
        System.out.println(wordDictionary.search("......."));
        System.out.println(wordDictionary.search("..n.r"));

        //expected
        //[null,null,null,null,null,null,null,null,null,true,false,true,true,true,false,true,true,false,false]
        return 0;
    }

}

class TrieST {
    private static final int R = 256; // extended ASCII

    private Node root;      // root of trie

    private static class Node {
        private Integer val;
        private Node[] next = new Node[R];
    }

    public TrieST() {

    }

     public Integer get(String key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        Node x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

    public boolean contains(String key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        Integer v = get(key);
        return v!= null;
    }

    private Node get(Node x, String key, int d) {

        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);

        Node next = x.next[c];
        if (c == '.') {

            for (int i = 0; i < R; i++) {
                Node xinext = x.next[i];
                if(xinext != null) {

                    Node res = get(xinext, key, d + 1);
                    if(res != null && res.val != null) {
                        Integer val = res.val;
                        return res;
                    }
                }
            }

        }

        return get(next, key, d + 1);
    }

    public void put(String key, Integer val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        //if (val == null) delete(key);
        else root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Integer val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            //if (x.val == null) n++;
            x.val = val;
            return x;
        }
        char c = key.charAt(d);

        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

//    public void delete(String key) {
//        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
//        root = delete(root, key, 0);
//    }
//
//    private Node delete(Node x, String key, int d) {
//        if (x == null) return null;
//        if (d == key.length()) {
//            x.val = null;
//        }
//        else {
//            char c = key.charAt(d);
//            x.next[c] = delete(x.next[c], key, d+1);
//        }
//
//        // remove subtrie rooted at x if it is completely empty
//        if (x.val != null) return x;
//        for (int c = 0; c < R; c++)
//            if (x.next[c] != null)
//                return x;
//        return null;
//    }

}

class WordDictionary {

    TrieST trie;

    /** Initialize your data structure here. */
    public WordDictionary() {
        trie = new TrieST();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        trie.put(word, 1);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return trie.contains(word);
    }

}

