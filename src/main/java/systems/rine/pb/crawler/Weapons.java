
package systems.rine.pb.crawler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weapons {

    @SerializedName("Hammer")
    @Expose
    public Weapon hammer;
    @SerializedName("Pistol")
    @Expose
    public Weapon pistol;
    @SerializedName("Rifle")
    @Expose
    public Weapon rifle;
    @SerializedName("Shield")
    @Expose
    public Weapon shield;
    @SerializedName("Speargun")
    @Expose
    public Weapon speargun;
    @SerializedName("Sword")
    @Expose
    public Weapon sword;

}
