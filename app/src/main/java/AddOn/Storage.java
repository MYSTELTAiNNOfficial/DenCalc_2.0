package AddOn;

import java.util.ArrayList;

public class Storage {
    private ArrayList<Barang> barangs = new ArrayList<>();
    private String golongan = new String();

    public ArrayList<Barang> getListBarangs() {
        return barangs;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }
}
