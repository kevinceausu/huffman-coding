import javax.swing.*;
import java.awt.*;

public class HuffmanGUI extends JFrame {
private static final long serialVersionUID = 1L;

    //field declarations
private HuffmanTree tree;
private JTextArea inputArea;
private JTextArea outputArea;
private JTextArea infoArea;

    //initial gui setup
public HuffmanGUI() {
 super("Huffman Coding Application");
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setSize(800, 600);
  setLayout(new BorderLayout(10, 10));

  //initialise huffman tree and generates codes
 tree = new HuffmanTree();
 tree.generateCodes();

 //user input panel
JPanel topPanel = new JPanel(new BorderLayout(5, 5));
topPanel.setBorder(BorderFactory.createTitledBorder("Enter Message"));
inputArea = new JTextArea(3, 40);
inputArea.setLineWrap(true);
inputArea.setWrapStyleWord(true);
topPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);

 			//buttons for interactions with app
JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
JButton btnGenerate = new JButton("Show Codes");
JButton btnEncode = new JButton("Encode Message");
JButton btnDecode = new JButton("Decode Binary");
JButton btnTree = new JButton("Show Tree");
buttonPanel.add(btnGenerate);
buttonPanel.add(btnEncode);
buttonPanel.add(btnDecode);
buttonPanel.add(btnTree);

 		//output area
 outputArea = new JTextArea(5, 40);
 outputArea.setEditable(false);
 outputArea.setBorder(BorderFactory.createTitledBorder("Output"));
 
        //information area (stats)
   infoArea = new JTextArea(10, 40);
   infoArea.setEditable(false);
   infoArea.setBorder(BorderFactory.createTitledBorder("Information"));

   			//add panels to jframe
   add(topPanel, BorderLayout.NORTH);
   add(buttonPanel, BorderLayout.CENTER);
        
   
        //bottom panel output + info panel
   JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 5, 5));
   bottomPanel.add(new JScrollPane(outputArea));
   bottomPanel.add(new JScrollPane(infoArea));
   add(bottomPanel, BorderLayout.SOUTH);

        	//button actions
  btnGenerate.addActionListener(e -> showCodes());
  btnEncode.addActionListener(e -> encodeMessage());
  btnDecode.addActionListener(e -> decodeBinary());
  btnTree.addActionListener(e -> showTree());

        //window placement
 setLocationRelativeTo(null); //center screen
 setVisible(true);//window appears
 toFront(); //bring window to front
    }

    //display Huffman codes
    private void showCodes() {
        infoArea.setText("");
        StringBuilder sb = new StringBuilder("Huffman Codes:\n\n");
        for (int i = 0; i < tree.getSymbols().size(); i++) {
            sb.append(tree.getSymbols().get(i))
              .append(": ")
              .append(tree.getCodes().get(i))
              .append("\n");
        }
        infoArea.setText(sb.toString());
    }

    
    //encode message and show results
    private void encodeMessage() {
        String message = inputArea.getText().trim().toUpperCase();
        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a message to encode.");
            return;
        }

   String encoded = tree.encode(message);
   String decoded = tree.decode(encoded);

  outputArea.setText("Encoded: " + encoded + "\nDecoded: " + decoded);
  

        //use backend method for compression stats
String stats = tree.getCompressionStats(message, encoded);
 infoArea.setText(stats);
    }

    
    //display decoding binary string
    private void decodeBinary() {
        String binary = inputArea.getText().trim();
        if (binary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a binary string to decode.");
            return;
        }
        String decoded = tree.decode(binary);
        outputArea.setText("Decoded: " + decoded);
        infoArea.setText("Decoded binary successfully.");
    }

    
    //show the huffman tree
    private void showTree() {
        infoArea.setText("");
        StringBuilder sb = new StringBuilder("Huffman Tree Structure:\n\n");
        captureTree(tree.getRoot(), "", sb);
        infoArea.setText(sb.toString());
    }

    //recursive helper for text-based tree printing
 private void captureTree(TreeNode node, String indent, StringBuilder sb) {
        if (node == null) return;
        if (node.getSymbol() != '\0')
            sb.append(indent).append(node.getSymbol()).append(" (").append(node.getFreq()).append(")\n");
        else
            sb.append(indent).append("* (").append(node.getFreq()).append(")\n");
        captureTree(node.getLeft(), indent + "  ", sb);
        captureTree(node.getRight(), indent + "  ", sb);
    }

    		//main method to launch gui
 public static void main(String[] args) {
        SwingUtilities.invokeLater(HuffmanGUI::new);
 }
}

