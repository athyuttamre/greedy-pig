import java.awt.* ;
class Slate extends Frame {
    // image is a buffer: when Slate users draw things, they
    // draw on the buffer. When the Slate gets painted, we
    // copy the image onto the screen.
    Image image;
    public Slate (int width, int height) {
        setBounds (100, 100, width, height);
        setBackground (Color.white);
        setVisible (true);
        image = createImage (width, height);
    }
    // when a Slate user asks for a Graphics object, we give
    // them one from the off-screen buffer.
    public Graphics getSlateGraphics () {
        return image.getGraphics ();
    }
    // normally update erases the screen and invokes paint, but
    // since we are overwriting the whole screen anyway, it is
    // slightly faster to override update and avoid clearing the
    // screen
    public void update (Graphics g) {
        paint (g);

    }
    // paint copies the off-screen buffer onto the screen
    public void paint (Graphics g) {
        if (image == null) return;
        g.drawImage (image, 0, 0, null);
    }
}