package Tables;

public class Goods {

    private String goodName;
    private String category;
    private int price;
    private int count;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodName='" + goodName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
