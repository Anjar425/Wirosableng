import greenfoot.*;

public class Menu extends Button {
    GreenfootSound menutheme = new GreenfootSound("menutheme.mp3");
    public boolean clicked = false;
    private boolean mouseOver = false;

    public Menu() {
        super("menu1.png", "menu2.png");
    }

    public void act() {
        if (!clicked && Greenfoot.mouseClicked(this)) {
            AudioPlayer.play(85, "buttonMenu.mp3");
            Greenfoot.setWorld(new MainMenu(menutheme)); // Pindah ke main menu saat tombol diklik
            clicked = true; // Set clicked ke true agar tidak memicu pemindahan dunia lagi
        }

        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null && mouse.getY() != 0 && mouse.getX() != 0) {
            if (mouse.getX() >= getX() - getImage().getWidth() / 2 && mouse.getX() <= getX() + getImage().getWidth() / 2
                    &&
                    mouse.getY() >= getY() - getImage().getHeight() / 2
                    && mouse.getY() <= getY() + getImage().getHeight() / 2) {
                if (!mouseOver) {
                    setImage("menu2.png"); // Ganti gambar tombol saat kursor masuk
                    mouseOver = true;
                }
            } else {
                if (mouseOver) {
                    setImage("menu1.png"); // Ganti gambar tombol saat kursor keluar
                    mouseOver = false;
                }
            }
        }
    }
}