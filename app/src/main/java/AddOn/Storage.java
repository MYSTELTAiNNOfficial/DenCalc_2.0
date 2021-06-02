package AddOn;

import java.util.ArrayList;

public class Storage {
    private ArrayList<Barang> barangs = new ArrayList<>();
    public String choosenGolongan[] = new String[3];

    public ArrayList<Barang> getListBarangs() {
        return barangs;
    }
}
