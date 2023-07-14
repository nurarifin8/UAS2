package uas;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
public class Connect {


    public class ConnectLaptop {
        private static modellaptop searchData(ArrayList<modellaptop> laptops, double searchRating) {
            for (modellaptop laptop : laptops) {
                if (laptop.getRating() == searchRating) {
                    return laptop;
                }
            }
            return null;
        }
        private static void selectionSort(ArrayList<modellaptop> laptops) {
            int n = laptops.size();
            for (int i = 0; i < n - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < n; j++) {
                    if (laptops.get(j).getRating() < laptops.get(minIndex).getRating()) {
                        minIndex = j;
                    }
                }
                modellaptop temp = laptops.get(minIndex);
                laptops.set(minIndex, laptops.get(i));
                laptops.set(i, temp);
            }
        }
        public static void main(String[] args) throws IOException {
            ConnectURI koneksiSaya = new ConnectURI();

            URL myAddress = koneksiSaya.buildURL("https://dummyjson.com/products/search?q=Laptop");
            String response = koneksiSaya.getresponsefromhttpUrl(myAddress);
            assert response != null;
            JSONArray responseJSON = new JSONArray(response);
            ArrayList<modellaptop> laptops = new ArrayList<>();
            for (int i = 0; i < responseJSON.length(); i++) {
                modellaptop lap = new modellaptop();
                JSONObject jsonObject = responseJSON.getJSONObject(i);
                lap.setId(jsonObject.getString("id"));
                lap.setTitle(jsonObject.getString("title"));
                lap.setDeskripsi(jsonObject.getString("description"));
                lap.setHarga(jsonObject.getInt("price"));
                lap.setDiskon(jsonObject.getString("discountPercentage"));
                lap.setRating(jsonObject.getInt("rating"));
                lap.setStok(jsonObject.getInt("stock"));
                lap.setBrand(jsonObject.getString("brand"));
                lap.setKategori(jsonObject.getString("category"));
                lap.setThumbnail(jsonObject.getString("thumbnail"));
            }
            System.out.println("Data JSON sebelum diurutkan:");
            for (modellaptop laptop : laptops) {
                System.out.println(laptop);
            }

            selectionSort(laptops);

            System.out.println("Data JSON setelah diurutkan:");
            for (modellaptop laptop : laptops) {
                System.out.println(laptop);
            }
            Scanner input = new Scanner(System.in);
            System.out.println("Masukan rating ");
            double searchRating = input.nextDouble();
            modellaptop searchData = searchData(laptops, searchRating);
            if (searchData != null) {
                System.out.println("Data dengan rating " + searchRating + ":");
                System.out.println(searchData);
            } else {
                System.out.println("Data dengan rating " + searchRating + " tidak ditemukan.");
            }
        }
    }
}
