package uas;

public class Pet {
    private int id;
    private String nama;
    private String spesies;
    private int umur;
    private String status;

    public Pet(int id, String nama, String spesies, int umur, String status) {
        this.id = id;
        this.nama = nama;
        this.spesies = spesies;
        this.umur = umur;
        this.status = status;
    }

    public int getId() { return id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getSpesies() { return spesies; }
    public void setSpesies(String spesies) { this.spesies = spesies; }

    public int getUmur() { return umur; }
    public void setUmur(int umur) { this.umur = umur; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
