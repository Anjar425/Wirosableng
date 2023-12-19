import greenfoot.*;

public class Story extends World {
    private int currentFrame = 1;
    public boolean lagu = true;
    public GreenfootSound opening = new GreenfootSound("openingEnding.mp3");
    public GreenfootSound naga = new GreenfootSound("openingNaga.mp3");

    public Story() {
        super(734, 430, 1, false);
        showFrame(); // Tampilkan frame gambar pertama saat dunia diinisialisasi

    }

    public void act() {
        // Cek apakah currentFrame berada pada frame 5 atau 6, lalu pindah ke frame
        // berikutnya secara otomatis
        opening.playLoop();
        opening.setVolume(100);
        if (currentFrame > 5) {
            opening.pause();
            opening.stop();
        }
        if (currentFrame == 7 && lagu) {
            AudioPlayer.play(80, "naga.mp3");
            lagu = false;
        }
        if (currentFrame == 5) {
            Greenfoot.delay(100); // Beri jeda waktu sebelum transisi ke frame 6 (50 act cycles)
            naga.playLoop();
            naga.setVolume(60);
            nextFrame();
        } else if (currentFrame == 1) {
            Greenfoot.delay(120); // Beri jeda waktu sebelum transisi ke frame 6 (50 act cycles)
            nextFrame();
        } else if (currentFrame == 2) {
            Greenfoot.delay(100); // Beri jeda waktu sebelum transisi ke frame 6 (50 act cycles)
            nextFrame();
        } else if (currentFrame == 6) {
            Greenfoot.delay(200); // Beri jeda waktu sebelum transisi ke frame 6 (50 act cycles)
            nextFrame();
        } else if (currentFrame == 11) {
            Greenfoot.delay(80); // Beri jeda waktu sebelum transisi ke frame 6 (50 act cycles)
            nextFrame();
        } else if (Greenfoot.mouseClicked(this)) {
            AudioPlayer.play(90, "buttonMenu.mp3");
            nextFrame(); // Pindah ke frame gambar berikutnya setiap kali layar diklik

        }
    }

    private void showFrame() {
        String imageName = currentFrame + ".png";
        GreenfootImage frameImage = new GreenfootImage(imageName);
        setBackground(frameImage);
    }

    private void nextFrame() {
        if (currentFrame < 15) {
            currentFrame++;
            showFrame(); // Tampilkan frame gambar berikutnya
        } else {
            naga.pause();
            naga.stop();

            Greenfoot.setWorld(new Intro());
        }
    }
}
