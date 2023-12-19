import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HeroPacket extends Actor {

    public long deltaTime;
    public long deltaTime2;
    public long lastFrame = System.nanoTime();
    public long lastFrame2 = System.nanoTime();
    public long rechargeTime;
    public long currentFrame = System.nanoTime();
    public int energyCost;
    public boolean recharged = false;
    public GreenfootImage recharge;
    public String name;
    public boolean selected = false;
    public MyWorld MyWorld;
    public boolean doneRechargeTime = false;
    public GreenfootImage image1;
    public GreenfootImage image2;

    public HeroPacket(long rechargeTime, boolean recharged, int energyCost, String name) {
        this.rechargeTime = rechargeTime;
        this.recharged = recharged;
        this.energyCost = energyCost;
        this.name = name;
        this.image1 = new GreenfootImage(name + "1.png");
        this.image2 = new GreenfootImage(name + "2.png");

    }

    public void addedToWorld(World world) {
        setRecharged(recharged);
        if (recharged) {
            doneRechargeTime = true;
            setImage(image1);
        } else {
            setImage(image2);
        }
        MyWorld = ((MyWorld) getWorld());
        recharge = new GreenfootImage(getImage().getWidth(), getImage().getHeight());
        int initialX = 52;
        int initialY = 130;

        // Memeriksa tabrakan dengan objek lain di dunia
        while (getWorld().getObjectsAt(initialX, initialY, HeroPacket.class).size() > 0) {

            initialY += 60;
        }

        setLocation(initialX, initialY);

    }

    public void act() {
        currentFrame = System.nanoTime();
        deltaTime = (currentFrame - lastFrame) / 1000000;
        deltaTime2 = (currentFrame - lastFrame2) / 1000000;

        if (deltaTime < rechargeTime && !doneRechargeTime) {
            if (!recharged && deltaTime2 > 500L) {

                setImage(name + "2.png");

                recharge.setColor(Color.BLACK);
                recharge.clear();
                int height = getImage().getHeight()
                        - (int) Math.round(getImage().getHeight() * ((double) deltaTime / rechargeTime));

                recharge.setTransparency(110);
                recharge.fillRect(0, 0, getImage().getWidth(), height);

                getImage().drawImage(recharge, 0, 0);
                lastFrame2 = System.nanoTime();
            }

        } else if (!recharged && !doneRechargeTime) {
            doneRechargeTime = true;
            setImage(image2);
        }

        if (MyWorld.heroBank.energycounter.energy >= energyCost) {
            if (!recharged) {
                if (deltaTime > rechargeTime) {

                    setRecharged(true);
                } else {
                    setRecharged(false);
                }
            }

        } else {
            setRecharged(false);
        }

        // Add your action code here.
    }

    public void startRecharge() {
        lastFrame = currentFrame;
        doneRechargeTime = false;
    }

    public void setRecharged(boolean charge) {
        if (recharged != charge) {
            recharged = charge;
            if (recharged) {
                setImage(image1);

            } else {
                setImage(image2);
            }
        }

    }

    public void setSelected(boolean bool) {
        selected = bool;
        if (selected) {
            setImage(image2);
        } else {
            setImage(image1);
        }
    }

    public boolean getCharge() {
        return recharged;
    }

    public boolean getSelected() {
        return selected;
    }

    public TransparentObject addImage() {
        return null;
    }

    public Hero getHero() {
        return null;
    }
}
