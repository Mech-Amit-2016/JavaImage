package imageprocessingmain;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

//************** this is a supporting class to create a image pannel to perform editing on images and to show outpu  ****//
public class ImagePanel extends JPanel implements java.awt.event.ActionListener, MouseMotionListener, MouseListener {

    private ImagePanel outputPanel;

    public void setOutputPanel(ImagePanel outputPanel) {
        this.outputPanel = outputPanel;
    }

    private boolean isDrawing = false;
    private int startx = 0, starty = 0, endx = 0, endy = 0;

    private BufferedImage img;

    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter1 = new FileNameExtensionFilter("PNG", "png");
        FileNameExtensionFilter extensionFilter2 = new FileNameExtensionFilter("JPG", "jpg");

        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(extensionFilter1);
        chooser.setFileFilter(extensionFilter2);

        Object ob = evt.getActionCommand();
        if (ob.equals("Open")) {
            int result = chooser.showOpenDialog(this);
            if (result != JFileChooser.APPROVE_OPTION) {
                return;
            }
            setIcon(ImageUtilities.readBufferedImage(chooser.getSelectedFile()));
        }
        if (ob.equals("Save")) {
            int result = chooser.showSaveDialog(this);
            if (result != chooser.APPROVE_OPTION) {
                return;
            }

            ImageUtilities.saveImage(getIcon(), chooser.getSelectedFile(), "png");
        }
    }

    public ImagePanel() {
        addMouseMotionListener(this);
        addMouseListener(this);
        JPopupMenu p = new JPopupMenu();
        JMenuItem openmenu = new JMenuItem("Open");
        openmenu.setMnemonic('O');
        openmenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        p.add(openmenu);
        JMenuItem savemenu = new JMenuItem("Save");
        savemenu.setMnemonic('S');
        savemenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        p.add(savemenu);
        savemenu.addActionListener(this);
        openmenu.addActionListener(this);
        setComponentPopupMenu(p);
    }

    public void setIcon(BufferedImage img) {
        this.img = img;
        repaint();
    }

    public BufferedImage getIcon() {
        return img;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img == null) {
            return;
        }
        Dimension d = getSize();
        //img=ImageUtilities.resizeImage(img,d.width, d.height);
        //g.drawImage(img, 0, 0, d.width, d.height, null);
        g.drawImage(img, 0, 0, null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//Graphics graphics=getGraphics();
//graphics.setColor(Color.red);
//graphics.draw3DRect(0, 0, 100, 100, true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

//   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        paintComponent(getGraphics());
//invalidate();;
        startx = e.getX();
        starty = e.getY();

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endx = e.getX();
        endy = e.getY();

        Graphics graphics = getGraphics();
        graphics.setColor(Color.red);
        graphics.draw3DRect(startx, starty, endx - startx, endy - starty, true);
        BufferedImage img = this.getIcon();
        BufferedImage newimage = ImageUtilities.cropImage(img, starty, startx, endy, endx);
        outputPanel.setIcon(newimage);

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
