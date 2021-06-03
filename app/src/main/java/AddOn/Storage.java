package AddOn;

import java.util.ArrayList;

public class Storage {
    public static ArrayList<Barang> barangs = new ArrayList<>();
    public static String[] choosenGolongan = new String[3];

    public ArrayList<Barang> getListBarangs() {
        return barangs;
    }

    public void setChoosenGolongan(String[] choosenGolongan) {
        this.choosenGolongan = choosenGolongan;
    }
}
