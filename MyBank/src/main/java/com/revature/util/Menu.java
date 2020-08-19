package com.revature.util;

import com.revature.screens.DashBoardScreen;
import com.revature.screens.LoginScreen;
import com.revature.screens.RegisterScreen;
import com.revature.screens.Screen;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public Menu() {
    }


    public void mainMenu(){

        int option = 372819387;

        while (option!=3){
            this.printMainMenu();
            System.out.println();
            System.out.println();

            System.out.print("please Enter the number: ");
            try{
                Scanner sc = new Scanner(System.in);
                option = sc.nextInt();

            } catch (InputMismatchException e){
                System.out.println("Please enter 1 , 2 or 3.");
                System.out.println();
                System.out.println();
            }
            switch (option) {
                case 1: this.login();break;
                case 2: this.register();break;
                case 3:  System.out.println("Exit...");break;
                default:
                    System.out.println(" Try again please......");

            }

        }
    }


    public void printMainMenu(){
        System.out.println("-----------------  MyBank  ---------------------");
        System.out.println();
        System.out.println("---------------  Main Menu ---------------------");
        System.out.println();
        System.out.println("                 1) Login                       ");
        System.out.println("                 2) Register                    ");
        System.out.println("                 3) Exit                        ");
        System.out.println();
        System.out.println("-----------------  END  ------------------------");
    }




    public void login(){
        Screen loginScreen = new LoginScreen();
        loginScreen.render();

    }
    public void register(){
        Screen registerScreen = new RegisterScreen();
        registerScreen.render();
    }


}
