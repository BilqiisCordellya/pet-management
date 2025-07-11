package uas;

import java.util.ArrayList;
import java.util.List;

public class PetControllers {
    private List<Pet> daftarPet = new ArrayList<>();
    private int nextId = 1;

    public void tambahPet(String nama, String spesies, int umur, String status) {
        Pet pet = new Pet(nextId++, nama, spesies, umur, status);
        daftarPet.add(pet);
    }

    public List<Pet> getAll() { return daftarPet; }

    public Pet cariById(int id) {
        return daftarPet.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    public boolean updatePet(int id, String namaBaru, String spesiesBaru, int umurBaru, String statusBaru) {
        Pet pet = cariById(id);
        if (pet != null) {
            pet.setNama(namaBaru);
            pet.setSpesies(spesiesBaru);
            pet.setUmur(umurBaru);
            pet.setStatus(statusBaru);
            return true;
        }
        return false;
    }

    public boolean hapusPet(int id) {
        Pet pet = cariById(id);
        return pet != null && daftarPet.remove(pet);
    }
}
