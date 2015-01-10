
public class CommonWords {

  /*
   *
   * Problem:
   * Given a really large file
   * find the most common 10 words.
   *
   * We'll assume we have the necessary abstractions
   * to read the file in chunks at a time if the file
   * is too large to fit into memory. Or at least
   * I'll implement it later if I'm bored.
   *
   * The problem we must first solve then is
   * how do we recognize the top 10 most used
   * words in an article.
   *
   * We have to support a couple key operations:
   * 1. Keeping track of the counts of words.
   * 2. Keeping track of the top 10 words.
   *
   * We can store the top 10 in a minHeap to make
   * checking if a word has entered a top 10
   * easily.
   *
   * We can store all the words in a hash table
   * but this would be grossly inefficient.
   *
   * We can improve on the required space by using
   * a trie. The leafs will then contain counts.
   * This will give us a space efficiency.
   *
   * We can make use of the tree structure to make
   * increaseKey operations relatively quick as well.
   *
   * 1st attempt:
   * Create a trie for all the words in the file.
   * for each word
   *   if w is in trie
   *     increase key
   *   else
   *     insert into trie
   *  put in heap if greater than min
   *
   * This likely has space efficiencies as we do not 
   *
   * 2nd attempt: Sort all the things.
   *
   * Sort the entire file (or use some external sort)
   * Count each word (through many files if can't fit in mem)
   * Compare each count with a min of a minHeap
   * if new count > currentMin
   *   pop then push new word
   *
   * fin
   * O(n 
   *
   *
   */
  public static solve(String url) {
  }

  public static void main(String[] args) {
  }

}
