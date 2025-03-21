import javax.swing.*;

import java.awt.*;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;

// Abstract parent class: Eyeball outline

abstract class Eyeball extends JPanel {

    private BufferedImage eyeballOutlineImage;

    public Eyeball() {

        loadImage();

    }

    

    protected abstract void loadImage();

    

    protected void setEyeballOutlineImage(String filePath) {

        try {

            eyeballOutlineImage = ImageIO.read(new File("outline-eye.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    

    protected BufferedImage getEyeballOutlineImage() {

        return eyeballOutlineImage;

    }

    

    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (eyeballOutlineImage != null) {

            g.drawImage(eyeballOutlineImage, 0, 0, this);

        }

    }

}

// Child class: Adds eyeball

class EyeballWithBase extends Eyeball {

    private BufferedImage eyeballImage;

    public EyeballWithBase() {

        super();

    }

    @Override

    protected void loadImage() {

        setEyeballOutlineImage("eyeball_outline.png");

        try {

            eyeballImage = ImageIO.read(new File("eyeball.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (eyeballImage != null) {

            g.drawImage(eyeballImage, 0, 0, this);

        }

    }

}

// Grandchild class: Adds iris

class EyeballWithIris extends EyeballWithBase {

    private BufferedImage irisImage;

    public EyeballWithIris() {

        super();

    }

    @Override

    protected void loadImage() {

        super.loadImage();

        try {

            irisImage = ImageIO.read(new File("iris.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (irisImage != null) {

            g.drawImage(irisImage, 0, 0, this);

        }

    }

}

// Great-grandchild class: Adds eyelashes

class EyeballWithEyelashes extends EyeballWithIris {

    private BufferedImage eyelashesImage;

    public EyeballWithEyelashes() {

        super();

    }

    @Override

    protected void loadImage() {

        super.loadImage();

        try {

            eyelashesImage = ImageIO.read(new File("lashes.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (eyelashesImage != null) {

            g.drawImage(eyelashesImage, 0, 0, this);

        }

    }

}

// Main class to display GUI

public class EyeballGUI {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Eyeball GUI");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setSize(300, 300);

            

            // Demonstrating polymorphism

            Eyeball eyeball = new EyeballWithEyelashes();

            

            frame.add(eyeball);

            frame.setVisible(true);

        });

    }

}