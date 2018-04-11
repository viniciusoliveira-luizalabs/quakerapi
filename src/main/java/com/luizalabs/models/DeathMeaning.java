package com.luizalabs.models;

/**
 * @author Ivo
 *
 */
public enum DeathMeaning {
	
	MOD_UNKNOWN(0),
    MOD_SHOTGUN(1),
    MOD_GAUNTLET(2),
    MOD_MACHINEGUN(3),
    MOD_GRENADE(4),
    MOD_GRENADE_SPLASH(5),
    MOD_ROCKET(6),
    MOD_ROCKET_SPLASH(7),
    MOD_PLASMA(8),
    MOD_PLASMA_SPLASH(9),
    MOD_RAILGUN(10),
    MOD_LIGHTNING(11),
    MOD_BFG(12),
    MOD_BFG_SPLASH(13),
    MOD_WATER(14),
    MOD_SLIME(15),
    MOD_LAVA(16),
    MOD_CRUSH(17),
    MOD_TELEFRAG(18),
    MOD_FALLING(19),
    MOD_SUICIDE(20),
    MOD_TARGET_LASER(21),
    MOD_TRIGGER_HURT(22),
    MOD_NAIL(23),
    MOD_CHAINGUN(24),
    MOD_PROXIMITY_MINE(25),
    MOD_KAMIKAZE(26),
    MOD_JUICED(27),
    MOD_GRAPPLE(28);

    private DeathMeaning(Integer id) {
        this.id = id;
    }

    public final Integer id;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Integer getId() {
        return id;
    }

    public static DeathMeaning valueOf(int ID) {

    	DeathMeaning[] values = DeathMeaning.values();
        for (DeathMeaning value : values) {
            if (value.getId() == ID) {
                return value;
            }
        }

        return MOD_UNKNOWN;
    }
}
