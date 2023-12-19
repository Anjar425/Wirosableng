import greenfoot.*;

public class Ending extends World {
    private int currentFrame = 0;
    public GreenfootSound menutheme = new GreenfootSound("menutheme.mp3");
    public GreenfootSound ending = new GreenfootSound("ending.mp3");
    public Ending() {
        super(734, 430, 1, false);
        showFrame(); // Tampilkan frame gambar pertama saat dunia diinisialisasi
    }

    public void act() {
        ending.playLoop();
        ending.setVolume(100);
    if (currentFrame < 3 ) {
            Greenfoot.delay(80); // Beri jeda waktu sebelum transisi ke frame 6 (50 act cycles)
            nextFrame();
        }
    else if (currentFrame == 6 ) {
            Greenfoot.delay(50); // Beri jeda waktu sebelum transisi ke frame 6 (50 act cycles)
            nextFrame();
        } 
    else if (currentFrame == 7 ) {
            Greenfoot.delay(70); // Beri jeda waktu sebelum transisi ke frame 6 (50 act cycles)
            nextFrame();
        } 
       else if (Greenfoot.mouseClicked(this)) {
            AudioPlayer.play(90, "buttonMenu.mp3");
            nextFrame(); // Pindah ke frame gambar berikutnya setiap kali layar diklik
        
    }
    }

    private void showFrame() {
        String imageName =  "e" + currentFrame + ".png";
        GreenfootImage frameImage = new GreenfootImage(imageName);
        setBackground(frameImage);
    }

    private void nextFrame() {
        if (currentFrame < 7) {
            currentFrame++;
            showFrame(); // Tampilkan frame gambar berikutnya
        } else {
            ending.pause();
            ending.stop();
            // Jika semua frame ditampilkan, kembali ke MainMenu
            Greenfoot.setWorld(new MainMenu(menutheme));
            
        }
    }
}
