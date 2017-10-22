package BlackBox;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Apply{
    
    Filter filter = new Filter();
    Login login = new Login();
    boolean skip = false;
    String description;
    int proposalCred;
    String connect;

    public void testApply(String url, String proposal, String propDesc, int budget){

        Tools.delay(2000);

        int mainBudget = budget;


        budget = mainBudget;

        login.driver.get(url);

        System.out.println("Testing url : " + url);

        Tools.delay(2000);

        checkConnects();

        shouldSkip();

        if(!skip) {
            int projectPrice;

            try {
                projectPrice = Integer.parseInt(login.driver.findElement(By.xpath(".//*[@id='main-container']/aside/section[1]/div/div[1]/div[2]/div/span")).getText());
            } catch (Exception e) {
                projectPrice = 0;
            }

            if (projectPrice > budget) {
                budget = projectPrice;
            }

            description = login.driver.findElement(By.xpath(".//*[@id='main-container']/div/div[3]/div")).getText();

            getProposalDescription(propDesc);

            getBudget(budget);

            getDeposit(budget);

            getProposal(proposal);

            //sendProposal();

            Tools.delay(3000);
        }

        login.driver.close();
        login.driver.quit();

    }

    public void applyWebDesign(String proposal, String propDesc, int budget){

        int mainBudget = budget;
        proposalCred = 2;
        Tools.print("Web Design Filter : " + filter.projectsWebDesign.size() + " products \n");

        for(int i = 0; i < filter.projectsWebDesign.size(); i++) { // projectsWebDesign.size()

            budget = mainBudget;

            login.driver.get(filter.projectsWebDesign.get(i)[1]);
            System.out.println((i+1) + " Applying url : " + filter.projectsWebDesign.get(i)[1]);

            checkConnects();

            Tools.delay(2000);

            shouldSkip();

            if(!skip) {
                int projectPrice = Integer.parseInt(filter.projectsWebDesign.get(i)[2]);

                if (projectPrice > budget) {
                    budget = projectPrice;
                }

                //description = login.driver.findElement(By.xpath(".//*[@id='main-container']/div/div[3]/div")).getText();

                getProposalDescription(propDesc);

                getBudget(budget);

                getDeposit(budget);

                getProposal(proposal);

                sendProposal();

                Tools.log((i) + " Bid : " + budget + "    Applied : " + filter.projectsWebDesign.get(i)[1]);
                Tools.logLinks(filter.projectsWebDesign.get(i)[1]);
                Tools.delay(3000);
            }

            if (proposalCred <= 1){
                i = filter.projectsWebDesign.size();
                System.out.println("You have no more Proposal Credits");
                Tools.log("You have no more Proposal Credits");
            }

        }

        Tools.log("\nProposal Credits : " + connect);

        login.driver.close();
        login.driver.quit();
    }

    public void applyAnimation(String proposalDescription, int budget){

        int price;



        for(int i = 0; i < filter.projects2D.size();i++) {
            login.driver.get(filter.projects2D.get(i)[1]);
            System.out.println("Applying url : " + filter.projects2D.get(i)[1]);
            Tools.delay(2000);

            //get price
            try{
                price = Integer.parseInt(login.driver.findElement(By.xpath(".//*[@id='main-container']/aside/section[1]/div/div[1]/div[2]/div/span")).getText());
            }catch (Exception e){price = 0;}

            description = login.driver.findElement(By.xpath(".//*[@id='main-container']/div/div[3]/div")).getText();

            System.out.println("price : " + price + ", description : " + description);

            //------------------------------------------keys-------------------------------------
            login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[1]/textarea")).sendKeys("test"); //webDesignProposal

            try {
                login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[2]/section[2]/div[1]/input")).sendKeys("Professional Animator");
            }catch (Exception e){
                login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[3]/section[2]/div[1]/input")).sendKeys("Professional Animator");
            }

            try {
                login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[2]/section[2]/div[4]/input")).sendKeys(String.valueOf(budget)); //amount
            }catch (Exception e){
                login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[3]/section[2]/div[4]/input")).sendKeys(String.valueOf(budget));
            }
            try {
                login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[4]/div[3]/input")).sendKeys(String.valueOf(budget)); //deposit
            }catch (Exception e){
                login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[5]/div[3]/input")).sendKeys("100");
            }

            Tools.delay(10000);
        }


    }





    public void getProposalDescription(String proposalDescription){

        //webDesignProposal description
        try {
            login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[2]/section[2]/div[1]/input")).sendKeys(proposalDescription);
        }catch (Exception e){
            try{login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[3]/section[2]/div[1]/input")).sendKeys(proposalDescription);
            }catch(Exception f){
                try{login.driver.findElement(By.xpath(".//*[@id=\"stream-message-box\"]/div/form/div/div[1]/div[3]/div/div/div[1]/section[2]/div[1]/input")).sendKeys(proposalDescription);
                }catch (Exception g){
                    try {
                        login.driver.findElement(By.xpath(".//*[@id=\"stream-message-box\"]/div/form/div/div[1]/div[4]/div/div/div[2]/section[2]/div[1]/input")).sendKeys(proposalDescription);
                    }catch (Exception h){
                        //login.driver.findElement(By.cssSelector(".form-control"));
                    }
                }
            }
        }
    }

    public void getBudget(int budget){

        try {
            login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[2]/section[2]/div[4]/input")).sendKeys(Keys.CONTROL + "a");
            login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[2]/section[2]/div[4]/input")).sendKeys(String.valueOf(budget));
        }catch (Exception e){
            try{
                login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[3]/section[2]/div[4]/input")).sendKeys(Keys.CONTROL + "a");
                login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[3]/section[2]/div[4]/input")).sendKeys(String.valueOf(budget));
            }
            catch (Exception f){
                login.driver.findElement(By.xpath(".//*[@id=\"stream-message-box\"]/div/form/div/div[1]/div[3]/div/div/div[1]/section[2]/div[4]/input")).sendKeys(Keys.CONTROL + "a");
                login.driver.findElement(By.xpath(".//*[@id=\"stream-message-box\"]/div/form/div/div[1]/div[3]/div/div/div[1]/section[2]/div[4]/input")).sendKeys(String.valueOf(budget));
            }
        }
    }

    public void getDeposit(int budget){

        int deposit = budget / 2;

        try {
            login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[4]/div[3]/input")).sendKeys(String.valueOf(deposit)); //deposit
        }catch (Exception e){
            try{login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[3]/div/div/div[5]/div[3]/input")).sendKeys(String.valueOf(deposit));}
            catch (Exception f){login.driver.findElement(By.xpath(".//*[@id=\"stream-message-box\"]/div/form/div/div[1]/div[3]/div/div/div[4]/div[3]/input")).sendKeys(String.valueOf(budget));}
        }
    }

    public void getProposal(String proposalText){

        String clientName = login.driver.findElement(By.xpath(".//*[@id='main-container']/aside/section[2]/div/div[1]/div[2]/div/h2/span[1]")).getText();

        proposalText = "Hi " + clientName + "\n" + proposalText;

        login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[1]/textarea")).sendKeys(proposalText);
    }



    public void checkConnects(){

        try {
            connect = login.driver.findElement(By.xpath(".//*[@id=\"stream-message-box\"]/div/form/div/div[3]/b")).getText();
            connect = connect.replace(" proposal credits", "");
            connect = connect.replace(" proposal credit", "");

            proposalCred = Integer.parseInt(connect);
            System.out.println("Proposal Credits: " + connect);
        }catch(Exception e){
            //Tools.print("Couldnt Find Credits");
        }
    }

    public void shouldSkip(){
        skip = false;

        try{
            String proposalText = login.driver.findElement(By.xpath(".//*[@id='stream-message-box']/div/form/div/div[1]/div[1]/textarea")).getText();
        }catch (Exception e){
            System.out.println("Skipping application");
            skip = true;
        }
    }

    public void sendProposal(){
        login.driver.findElement(By.xpath(".//*[@id=\"stream-message-box\"]/div/form/div/div[2]/div[2]/button")).click();
    }
}

