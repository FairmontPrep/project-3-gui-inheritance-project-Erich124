import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// Abstract parent class
abstract class ImageLayer extends JPanel {
    private BufferedImage image;
    protected String description;

    public ImageLayer(String filePath, String description) {
        this.description = description;
        loadImage(filePath);
    }

    private void loadImage(String filePath) {
        try {
            image = ImageIO.read(new File(filePath));
            if (image == null) {
                System.out.println("Error loading image: " + filePath);
            } else {
                System.out.println("Successfully loaded: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("Failed to load image: " + filePath);
            e.printStackTrace();
        }
    }

    public String getDescription() {
        return description;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}

// Background layer
class BackgroundLayer extends ImageLayer {
    public BackgroundLayer() {
        super("/mnt/data/Background.png", "Background layer added. ");
    }
}

// Child class adding layer 1
class Layer1 extends BackgroundLayer {
    public Layer1() {
        super();
        description += "Layer 1: Base layer added. ";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage img = ImageIO.read(new File("图层 1.png"));
            g.drawImage(img, 0, 0, this);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.BLACK);
            g.drawString("Congratulation!", 800, 50);
        } catch (IOException e) {
            System.out.println("Error loading Layer 1");
            e.printStackTrace();
        }
    }
}

// Child class adding layer 3
class Layer3 extends Layer1 {
    public Layer3() {
        super();
        description += "Layer 3: Mid layer added. ";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage img = ImageIO.read(new File("图层 3.png"));
            g.drawImage(img, 0, 0, this);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.BLACK);
            g.drawString("You", 950, 50);
        } catch (IOException e) {
            System.out.println("Error loading Layer 3");
            e.printStackTrace();
        }
    }
}

// Child class adding layer 4
class Layer4 extends Layer3 {
    public Layer4() {
        super();
        description += "Layer 4: Additional details added. ";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage img = ImageIO.read(new File("图层 4.png"));
            g.drawImage(img, 0, 0, this);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.BLACK);
            g.drawString("got", 1000, 50);
        } catch (IOException e) {
            System.out.println("Error loading Layer 4");
            e.printStackTrace();
        }
    }
}

// Child class adding layer 5
class Layer5 extends Layer4 {
    public Layer5() {
        super();
        description += "Layer 5: Additional details added. ";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage img = ImageIO.read(new File("图层 5.png"));
            g.drawImage(img, 0, 0, this);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.BLACK);
            g.drawString("a", 1040, 50);
        } catch (IOException e) {
            System.out.println("Error loading Layer 5");
            e.printStackTrace();
        }
    }
}

// Child class adding layer 6
class Layer6 extends Layer5 {
    public Layer6() {
        super();
        description += "Layer 6: Additional details added. ";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage img = ImageIO.read(new File("图层 6.png"));
            g.drawImage(img, 0, 0, this);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.BLACK);
            g.drawString("Style", 1140, 50);
        } catch (IOException e) {
            System.out.println("Error loading Layer 6");
            e.printStackTrace();
        }
    }
}

// Child classes for layer 7 variants
class Layer7 extends Layer6 {
    public Layer7() {
        super();
        description += "Standard Layer 7 added. You got a normal human! ";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage img = ImageIO.read(new File("图层 7.png"));
            g.drawImage(img, 0, 0, this);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.BLACK);
            g.drawString("normal             ---Human!!!", 1060, 50);
        } catch (IOException e) {
            System.out.println("Error loading Layer 7");
            e.printStackTrace();
        }
    }
}

class Layer7Copy extends Layer6 {
    private boolean shouldDisplay;

    public Layer7Copy() {
        super();
        shouldDisplay = Math.random() < 0.5;
        description += shouldDisplay ? "Alternative Layer 7 added. " : "";
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage img = ImageIO.read(new File("图层 7 copy.png"));
            g.drawImage(img, 0, 0, this);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.BLACK);
            g.drawString("hidden             ---Avatar!!!", 1060, 50);
        } catch (IOException e) {
            System.out.println("Error loading Layer 7 Copy");
            e.printStackTrace();
        }
    }
}

// Main class
public class ImageDisplay {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Layered Image Display");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            
            ImageLayer finalLayer = Math.random() < 0.5 ? new Layer7() : new Layer7Copy();
            JLabel label = new JLabel(finalLayer.getDescription());
            frame.add(label, BorderLayout.SOUTH);
            frame.add(finalLayer, BorderLayout.CENTER);
            
            frame.setVisible(true);
            frame.repaint(); // Ensure GUI updates
        });
    }
}
