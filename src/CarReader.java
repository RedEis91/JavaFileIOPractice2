import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Jared on 8/1/17.
 */
public class CarReader {
    public static void main(String[] args) {

        ArrayList<Car> carsList = readFile("./cars.txt");

        if (carsList==null) {
            System.out.println("Issue with file...closing");
            return;
        }


        //output the list:
        for (Car c: carsList) {
            //output each car as object and
            //let the objects toString() do the formatting work
            System.out.println(c);
        }


    }
    public static ArrayList<Car> readFile (String filename) {
        //need a place to store objects, so create an array list
        ArrayList<Car> carsList = new ArrayList<Car>();

        //reading stuff in:
        //open file

        //go to next line

        //Situations you can't control: does the file exist? did someone delete the file?
        //did someone overwrite the file?

        //caught exception, FileReader class automatically will prompt for a Try/Catch block
        try{

            //a Path is a java object that isn't a file, it's a path to a file
            //Path's class library has a static method called .get()
            Path carsPath = Paths.get("./cars.txt");
            //the Paths object creates the method to create a path

            //you can't read from a Path, you need to read from a file
            //you can create a File from a path
            File carsFile = carsPath.toFile();


            FileReader fileRdr = new FileReader(carsFile);
            //important: BufferedReader is created from FileReader that was just instantiated
            BufferedReader in = new BufferedReader(fileRdr);

            //like nextLine for Scanner, except for a File instead of user input stream
            //reads in the first line
            String line = in.readLine();
            //as long as there is another line:
            while (line != null) {

                //breaks line apart based on tabs
                String[] details = line.split("\t");
                //"Hey line, split up into tokens based on tab"

                //prevents Exception
                //if line doesn't have correct number of elements, it will break
                if (details.length < 4) {
                    System.out.println("Bad line format --halting read");
                    //breaks out of loop but will still return carsList
                    break;
                }

                //Stores first token from line and stores in Make variable in order to
                //takes the first item and put it into the car(constructor)'s Make
                String make = details[0];

                //takes the second item and puts it into the car's Model
                String model = details[1];

                //takes the third item, *turn it into an int*, and put into Year
                //parseInt takes a string and turns it into an Int
                int year = Integer.parseInt(details[2]);

                //takes 4th item, turn into double, put into car's Price
                double price = Double.parseDouble(details[3]);

                //constructs a new Car object from this data
                Car c = new Car(make, model, year, price);

                //adds the newly instantiated Car object into carsList ArrayList
                carsList.add(c);

                //read in the next line for the next iteration, so that it is not infinite loop
                line = in.readLine();

            }
        }
        //catches any input / output exception.  If file is empty, if it exists but you
        //don't have read/write access
        catch(IOException e) {
            //print out contents exception
            System.out.println(e);
            return null;
        }
        return carsList;
    }
}
