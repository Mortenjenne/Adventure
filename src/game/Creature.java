package game;

    public interface Creature {

        public int attack();

        public boolean isDead();

        public String getDescription();

        public void takeDamage(int damage);

        public int getHealth();

        public String getName();

    }