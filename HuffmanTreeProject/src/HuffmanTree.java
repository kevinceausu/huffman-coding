import java.util.*;

public class HuffmanTree {
	

    //Instance variables
private TreeNode root; //top node of the tree
private ArrayList<Character> symbols = new ArrayList<>();
private ArrayList<String> codes = new ArrayList<>();

    //Constructor to build HuffmanTree with the data from the txt file provided
 public HuffmanTree() {
        List<TreeNode> nodeList = getPredefinedFrequencies();
        buildTree(nodeList);
    }

     //the frequencies give in the txt data attached to an array list
   private List<TreeNode> getPredefinedFrequencies() {
	   List<TreeNode> nodeList = new ArrayList<>();

        nodeList.add(new TreeNode(128, 'Z'));
        nodeList.add(new TreeNode(188, 'J'));
        nodeList.add(new TreeNode(205, 'Q'));
        nodeList.add(new TreeNode(315, 'X'));
        nodeList.add(new TreeNode(1257, 'K'));
        nodeList.add(new TreeNode(2019, 'V'));
        nodeList.add(new TreeNode(2715, 'B'));
        nodeList.add(new TreeNode(3316, 'P'));
        nodeList.add(new TreeNode(3693, 'G'));
        nodeList.add(new TreeNode(3819, 'W'));
        nodeList.add(new TreeNode(3853, 'Y'));
        nodeList.add(new TreeNode(4200, 'F'));
        nodeList.add(new TreeNode(4761, 'M'));
        nodeList.add(new TreeNode(4943, 'C'));
        nodeList.add(new TreeNode(5246, 'U'));
        nodeList.add(new TreeNode(7253, 'L'));
        nodeList.add(new TreeNode(7874, 'D'));
        nodeList.add(new TreeNode(10795, 'H'));
        nodeList.add(new TreeNode(10977, 'R'));
        nodeList.add(new TreeNode(11450, 'S'));
        nodeList.add(new TreeNode(12666, 'N'));
        nodeList.add(new TreeNode(13318, 'I'));
        nodeList.add(new TreeNode(14003, 'O'));
        nodeList.add(new TreeNode(14810, 'A'));
        nodeList.add(new TreeNode(16587, 'T'));
        nodeList.add(new TreeNode(21912, 'E'));

        	//sorting nodes from lowest freq to highest
        nodeList.sort(Comparator.comparingInt(TreeNode::getFreq));
         return nodeList;
   }

    //build huffman tree by taking the lowest freq nodes and combining them into a parent
 private void buildTree(List<TreeNode> nodeList) {
   while (nodeList.size() > 1) {
	   TreeNode left = nodeList.remove(0);
       TreeNode right = nodeList.remove(0);
       TreeNode parent = new TreeNode(left.getFreq() + right.getFreq(), '\0', left, right);
       nodeList.add(parent);
       nodeList.sort(Comparator.comparingInt(TreeNode::getFreq));
       }
        root = nodeList.get(0);
    }

    	//generate huffman codes by traversing the tree
   public void generateCodes() {
        symbols.clear();
        codes.clear();
        generateCodesRecursive(root, "");
    }
    
   			//recursive method
 private void generateCodesRecursive(TreeNode node, String code) {
   if (node == null) return;

          //store the given codes
        if (node.getSymbol() != '\0') {
            symbols.add(node.getSymbol());
            codes.add(code);
        }

        	//recurse left (0) and right (1)
   generateCodesRecursive(node.getLeft(), code + "0");
   generateCodesRecursive(node.getRight(), code + "1");
    }

    		//encode a message
    public String encode(String message) {
       StringBuilder encoded = new StringBuilder();
       message = message.toUpperCase(); //match A–Z

       for (int i = 0; i < message.length(); i++) {
         char ch = message.charAt(i);

            //skip characters not present in our symbol list
       int idx = symbols.indexOf(ch);
       if (idx == -1) continue;

           encoded.append(codes.get(idx));
       }
         return encoded.toString();
    }

    		//decode a binary string
  public String decode(String binary) {
  StringBuilder decoded = new StringBuilder();
    TreeNode current = root;

        for (int i = 0; i < binary.length(); i++) {
          char bit = binary.charAt(i);

          if (bit == '0') {
                current = current.getLeft();
            } else if (bit == '1') {
                current = current.getRight();
            } else {
              continue;
          }

            //if we reached a leaf (a real symbol)
      if (current.getSymbol() != '\0') {
        decoded.append(current.getSymbol());
         current = root;
            }
        }
        	return decoded.toString();
    }

    		//Return compression stats
 public String getCompressionStats(String message, String encoded) {
   int validCharCount = 0;
     message = message.toUpperCase();

     for (int i = 0; i < message.length(); i++) {
       if (symbols.contains(message.charAt(i))) {
         validCharCount++;
           }
 }

int originalBits = validCharCount * 7;
int compressedBits = encoded.length();
//avoid dividing by 0, divide original message bits by encoded bits to get a ratio
double ratio = (compressedBits == 0) ? 0 : (double) originalBits / compressedBits;
//avoid dividing by 0, formula for getting a the reduction percent
double reductionPercent = (compressedBits == 0) ? 0 : 100 * (1 - ((double) compressedBits / originalBits));

        
//final string will be displayed in gui app, %d returns an integer while %2f returns doubles with 2 decimal places
 return String.format(
  "Compression statistics:\n" +
  "-----------------------\n" +
  "Original size (7-bit ASCII): %d bits\n" +
  "Compressed size (Huffman):   %d bits\n" +
  "Compression ratio: %.2f : 1\n" +
  "Space reduction:   %.2f%%\n",
   originalBits, compressedBits, ratio, reductionPercent
        );
    }

    	//print all letters with the codes
 public void printCodes() {
    System.out.println("\nHuffman Codes:\n");
     for (int i = 0; i < symbols.size(); i++) {
       System.out.println(symbols.get(i) + ": " + codes.get(i));
     }
    }

    			//print huffman tree
  public void printTree(TreeNode node, String indent) {
     if (node == null) return;

      if (node.getSymbol() != '\0')
         System.out.println(indent + node.getSymbol() + " (" + node.getFreq() + ")");
      else
         System.out.println(indent + "* (" + node.getFreq() + ")");

        	printTree(node.getLeft(), indent + "  ");
        	printTree(node.getRight(), indent + "  ");
    }

    			//accessors for gui 
public TreeNode getRoot() {
     return root;
    }

 public ArrayList<Character> getSymbols() {
    return symbols;
    }

  public ArrayList<String> getCodes() {
    return codes;
    }

    			//console test
  public static void main(String[] args) {
     HuffmanTree tree = new HuffmanTree();
     tree.printTree(tree.getRoot(), "");
     tree.generateCodes();
     tree.printCodes();
        
        String message = "HELLO";
        String encoded = tree.encode(message);
        String decoded = tree.decode(encoded);

        System.out.println("\nOriginal : " + message);
        System.out.println("Encoded  : " + encoded);
        System.out.println("Decoded  : " + decoded);
        System.out.println(tree.getCompressionStats(message, encoded));
    }
}


