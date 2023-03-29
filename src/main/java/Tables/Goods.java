package Tables;

public class Goods {
    private int id;
    private String goodName;
    private String category;
    private float price;
    private int count;
    private String photoURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodName='" + goodName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", photoURL='" + photoURL + '\'' +
                '}';
    }
}
