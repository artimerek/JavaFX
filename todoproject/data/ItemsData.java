package todoproject.data;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

/*
    This project is on early stage and for practising file. After some versions i will dont that with databases.
 */

public class ItemsData { // singleton class
    private static ItemsData instance = new ItemsData();
    private static String fileName = "Items.txt";

    private List<Items> items;
    private DateTimeFormatter formatter;

    public static ItemsData getInstance() {
        return instance;
    }

    private ItemsData(){
        // private constructor to don't allow to create another instances
        formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public void loadItems() throws IOException {
        items = FXCollections.observableArrayList();
        Path path = Paths.get(fileName);
        BufferedReader bufferedReader = Files.newBufferedReader(path);

        String input;

        try{
            while ((input = bufferedReader.readLine()) != null){ // retrieving items from file then save to arraylist
                String[] items = input.split("\t");
                String shortDescription = items[0];
                String fullDescription = items[1];
                String deadline = items[2];

                LocalDate date = LocalDate.parse(deadline,formatter);
                Items item = new Items(date,shortDescription,fullDescription);

                this.items.add(item);
            }
        }finally {
            if(bufferedReader !=null){
                bufferedReader.close();
            }
        }
    }

    public void safeItems() throws IOException{
        Path path = Paths.get(fileName);
        BufferedWriter bufferedWriter = Files.newBufferedWriter(path);
        try{
            Iterator<Items> itemsIterator = items.iterator();
            while (itemsIterator.hasNext()){
                Items item = itemsIterator.next();
                bufferedWriter.write(String.format("%s\t%s\t%s",
                        item.getDeadline().format(formatter),
                        item.getShortDescription(),
                        item.getFullDescription()));
                bufferedWriter.newLine();
            }
        }finally {
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
        }
    }
}
