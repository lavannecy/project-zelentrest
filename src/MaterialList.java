import java.util.ArrayList;

public class MaterialList {
    private ArrayList<String> materials;

    public MaterialList() {
        materials = new ArrayList<>();
        materials.add("Сіль");
        materials.add("Пісок");
        materials.add("Добриво");
    }

    public boolean contains(String material) {
        return materials.contains(material);
    }
}
