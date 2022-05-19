package DataAccess;

import BusinessLogic.BaseProduct;
import BusinessLogic.MenuItem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CSVReader {

    public static <T> Predicate<T> distinct(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    public List<MenuItem> extractFromCSV() throws IOException {
        Path path = Path.of("src", "main", "resources", "products.csv");
        List<MenuItem> list = null;
        list = Files.lines(path)
                .skip(1)
                .map(line -> {
                    String[] fields = line.split(",");
                    return new BaseProduct(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
                })
                .filter(distinct(baseProduct -> baseProduct.getTitle()))
                .collect(Collectors.toList());
        return list;
    }
    public String[] getColumns(){
        String path = "products.csv";
        String line = "";
        String[] values = {""};
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path));
            line = buffer.readLine();
            values = line.split(",");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }
    public ArrayList<MenuItem> getData(){
        ArrayList<MenuItem> itemsList = new ArrayList<>();
        try {
            Path path = Path.of("src", "main", "resources", "products.csv");
            BufferedReader buffer = new BufferedReader(new FileReader(String.valueOf(path)));
            String line;
            while((line = buffer.readLine()) != null){
                String[] values = line.split(",");
                itemsList.add(new BaseProduct(values[0], Double.parseDouble(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), Integer.parseInt(values[6])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return itemsList;
    }
}
