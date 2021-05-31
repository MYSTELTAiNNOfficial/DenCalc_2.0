package AddOn;

public class Barang {
    private int id, watt_barang, jumlah, total_pemakaian;
    private String tipe_barang;

    public Barang() {
        this.id = -1;
        this.watt_barang = -1;
        this.jumlah = -1;
        this.total_pemakaian = -1;
        this.tipe_barang = "";
    }

    public Barang(String tipe_barang, int total_pemakaian,int watt_barang, int jumlah) {
        this.id = 0;
        this.watt_barang = watt_barang;
        this.jumlah = jumlah;
        this.total_pemakaian = total_pemakaian;
        this.tipe_barang = tipe_barang;
    }

    public Barang(int id, String tipe_barang, int total_pemakaian,int watt_barang, int jumlah) {
        this.id = id;
        this.watt_barang = watt_barang;
        this.jumlah = jumlah;
        this.total_pemakaian = total_pemakaian;
        this.tipe_barang = tipe_barang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWatt_barang() {
        return watt_barang;
    }

    public void setWatt_barang(int watt_barang) {
        this.watt_barang = watt_barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getTotal_pemakaian() {
        return total_pemakaian;
    }

    public void setTotal_pemakaian(int total_pemakaian) {
        this.total_pemakaian = total_pemakaian;
    }

    public String getTipe_barang() {
        return tipe_barang;
    }

    public void setTipe_barang(String tipe_barang) {
        this.tipe_barang = tipe_barang;
    }
}