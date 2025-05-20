package com.example.Restaurant_Management.GenerateSecretKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Awards {


    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();


        list.add(new Product(1,"Phần thưởng 1",1000));
        list.add(new Product(2,"Phần thưởng 2",500));
        list.add(new Product(3,"Phần thưởng 3",200));
        list.add(new Product(4,"Phần thưởng 4",100));
        list.add(new Product(5,"Phần thưởng 5",50));
        list.add(new Product(6,"Phần thưởng 6",20));
        list.add(new Product(7,"Phần thưởng 7",10));
        list.add(new Product(8,"Phần thưởng 8",5));

        Random rand = new Random();

        Scanner src = new Scanner(System.in);

        while(true){
            System.out.print("Bạn có muốn nhận phần thưởng không? (y/n): ");
            String userInput = src.nextLine().toLowerCase();

            if(userInput.equals("y")){
                int randomIndex = rand.nextInt(list.size());

                Product product = list.get(randomIndex);

                System.out.println("Phần thưởng ngẫu nhiên là: "+product.getName());
            }else if(userInput.equals("n")){
                break;
            }
        }
    }
}
