public class Main2 {}

    //private final static String JAXB_XML_GAME_PACKAGE_NAME = "schemaGenerated";

/*
    public static void main(String[] args) {


   */
/*     try {
            InputStream inputStream = new FileInputStream(new File("C:\\Users\\97254\\IdeaProjects\\BreakingTheEnigma\\EngimaEngine\\src\\resources\\ex1-sanity-small.xml"));
            CTEEnigma descriptor = deserializeFrom(inputStream);
            System.out.println("name of first country is: " + descriptor.getCTEMachine().getABC());
            Engine.TheEnigmaEngine.MediatorforSchema mediatorforSchema= new Engine.TheEnigmaEngine.MediatorforSchema(descriptor);
            System.out.println(mediatorforSchema.xmlValidation()+"XML");
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }*//*


        Start();
    }
*/

/*    private static void Start() {
        Scanner scanner = new Scanner(System.in);
        EngineManager engineManager = new EngineManager();
        String loadStart = scanner.nextLine();
        if (fileNameValidation(loadStart,engineManager)) {
            try {
                engineManager.load(loadStart);
                System.out.println("The xml was uploaded successfully");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean fileNameValidation(String str,EngineManager engineManager) {

        MachineDTO machineDTO= engineManager.getAllErrorsRelatedToFilePath(str);
        if (machineDTO.getListOfException().size() == 0) {
           return true;
        }
        else {
            for (Exception exception : machineDTO.getListOfException()) {
                System.out.println(exception.getMessage());
                System.out.println("******************");
            }
            return false;
        }
    }
}*/
/*
        Scanner scanner = new Scanner(in);

        String fileName = scanner.nextLine().trim();

            int len = fileName.length();
            if (len < 4) {
                System.out.println("File full name is too short!");
            } else if (!fileName.endsWith(".xml")) {
                System.out.println("This is not a full path of a xml file!");
            } else {
                System.out.println("Good");
                File f = new File(fileName);
            }
        }*/



/*    private static CTEEnigma deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
        Unmarshaller u = jc.createUnmarshaller();
        return (CTEEnigma) u.unmarshal(in);
    }

    private Boolean isFileNameValid(final String fileName) {
        try {
            if (med.isFileNameValid(fileName)) {
                return true;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }*/




/*    public Boolean isFileLoaded() {
        return targetsGraph != null;
    }*/
/*
    public Boolean loadFile(String fileName)  {
    GPUPDescriptor descriptor;
    try (InputStream inputStream = new FileInputStream(fileName)) {
        descriptor = deserializeFrom(inputStream);
    }
    targetsGraph = new Graph(descriptor);
    return true;
}
    public Boolean isFileNameValid(final String fileName)  {
        int len = fileName.length();
        if (len < 4) {
            throw new IOException("File full name is too short!");
        }
        if (!fileName.endsWith(".xml")) {
            throw new IOException("This is not a full path of a xml file!");
        }
        return true;
    }
*/

 /*       List<Engine.TheEnigmaEngine.Pair> rotorStructure=new ArrayList<>();
        int amount=6;
        rotorStructure.add(new Engine.TheEnigmaEngine.Pair('A','F'));
        rotorStructure.add(new Engine.TheEnigmaEngine.Pair('B','E'));
        rotorStructure.add( new Engine.TheEnigmaEngine.Pair('C','D'));
        rotorStructure.add(new Engine.TheEnigmaEngine.Pair('D','C'));
        rotorStructure.add(new Engine.TheEnigmaEngine.Pair('E','B'));
        rotorStructure.add(new Engine.TheEnigmaEngine.Pair('F','A'));
        Engine.TheEnigmaEngine.Rotor rotor=new Engine.TheEnigmaEngine.Rotor("1",'C',"4",6,rotorStructure);


        List<Engine.TheEnigmaEngine.Pair> rotorStructure2=new ArrayList<>();
        rotorStructure2.add(new Engine.TheEnigmaEngine.Pair('A','B'));
        rotorStructure2.add(new Engine.TheEnigmaEngine.Pair('B','A'));
        rotorStructure2.add( new Engine.TheEnigmaEngine.Pair('C','D'));
        rotorStructure2.add(new Engine.TheEnigmaEngine.Pair('D','C'));
        rotorStructure2.add(new Engine.TheEnigmaEngine.Pair('E','F'));
        rotorStructure2.add(new Engine.TheEnigmaEngine.Pair('F','E'));
        Engine.TheEnigmaEngine.Rotor rotor2=new Engine.TheEnigmaEngine.Rotor("1",'C',"1",6,rotorStructure2);

        List<Engine.TheEnigmaEngine.Pair> rotorStructur3=new ArrayList<>();
        rotorStructur3.add(new Engine.TheEnigmaEngine.Pair('A','E'));
        rotorStructur3.add(new Engine.TheEnigmaEngine.Pair('B','B'));
        rotorStructur3.add( new Engine.TheEnigmaEngine.Pair('C','D'));
        rotorStructur3.add(new Engine.TheEnigmaEngine.Pair('D','F'));
        rotorStructur3.add(new Engine.TheEnigmaEngine.Pair('E','C'));
        rotorStructur3.add(new Engine.TheEnigmaEngine.Pair('F','A'));
        Engine.TheEnigmaEngine.Rotor rotor3=new Engine.TheEnigmaEngine.Rotor("13",'C',"1",6,rotorStructur3);

        System.out.println(rotor.getRotorStructure().toString()+"Engine.TheEnigmaEngine.Rotor 1");
        System.out.println(rotor2.getRotorStructure().toString()+"Engine.TheEnigmaEngine.Rotor 2");
        System.out.println("AFTER");
        List<Engine.TheEnigmaEngine.Rotor> arrRotors=new ArrayList<>();
        arrRotors.add(rotor);
        //arrRotors.add((rotor2));
        arrRotors.add((rotor3));
        Engine.TheEnigmaEngine.RotorsSet rotorsSet=new Engine.TheEnigmaEngine.RotorsSet(arrRotors);


        List<Engine.TheEnigmaEngine.Pair> reflectorLisr=new ArrayList<>();
        reflectorLisr.add(new Engine.TheEnigmaEngine.Pair('1','4'));
        reflectorLisr.add(new Engine.TheEnigmaEngine.Pair('2','5'));
        reflectorLisr.add( new Engine.TheEnigmaEngine.Pair('3','6'));
        Engine.TheEnigmaEngine.Reflector  reflector=new Engine.TheEnigmaEngine.Reflector("2",reflectorLisr);
        System.out.println(reflector.getReflectorStructure() +"Engine.TheEnigmaEngine.Reflector");

       //System.out.println(reflector.getExitIndexFromTheReflector(6)+ "ExitIndexFromTheReflector");

        List<Engine.TheEnigmaEngine.Pair> reflectorLisr2=new ArrayList<>();
        reflectorLisr2.add(new Engine.TheEnigmaEngine.Pair('1','2'));
        reflectorLisr2.add(new Engine.TheEnigmaEngine.Pair('3','4'));
        reflectorLisr2.add(new Engine.TheEnigmaEngine.Pair('5','6'));
        Engine.TheEnigmaEngine.Reflector  reflector2=new Engine.TheEnigmaEngine.Reflector("2",reflectorLisr2);

        Engine.TheEnigmaEngine.Keyboard keyboard=new Engine.TheEnigmaEngine.Keyboard("ABCDEF");

        List<Engine.TheEnigmaEngine.Pair> plugs=new ArrayList<>();
        //plugs.add(new Engine.TheEnigmaEngine.Pair('C','B'));
        plugs.add(new Engine.TheEnigmaEngine.Pair('A','F'));
        Engine.TheEnigmaEngine.PlugsBoard plugsBoard=new Engine.TheEnigmaEngine.PlugsBoard(keyboard,plugs);
       // Engine.TheEnigmaEngine.PlugsBoard plugsBoard=new Engine.TheEnigmaEngine.PlugsBoard(keyboard,plugs,amountToSwap);



        Engine.TheEnigmaEngine.TheMachineEngine theMachineEngine=new Engine.TheEnigmaEngine.TheMachineEngine(rotorsSet,reflector,keyboard,plugsBoard);
        Scanner scanner = new Scanner(System.in);

        char userIntegerInput = 0;
        boolean validInput=true;
        do {
            System.out.print("Enter an integer: ");

                userIntegerInput = scanner.next().charAt(0);
               *//* if((userIntegerInput=='\t')||(userIntegerInput=='\r\n')||(userIntegerInput=='\r')){
                    validInput = false;
                }
                else {*//*
            System.out.println(theMachineEngine.manageDecode(userIntegerInput));

             //  }


        } while (validInput == true);*/
        //Scanner sc = new Scanner(System.in);
        //char c = (char) System.in.read();
//        int i=0;
//        while ((c!='\t')&&(c!='\n')&&(c!='\r')) {
//            c = sc.next().charAt(i);
//        }

      //  System.out.println(theMachineEngine.manageDecode('F'));


        //rotor.spinRotor();
       // System.out.println(rotor.getValueFromRotorByIndex(keyboard.getIndexFromKeyboard('B')));
        //System.out.println(keyboard.getIndexFromKeyboard('C'));

        //System.out.println(reflector2.getReflectorStructure() +"Engine.TheEnigmaEngine.Reflector");

        //System.out.println(reflector2.getExitIndexFromTheReflector(6)+ "ExitIndexFromTheReflector");

  /*      rotorsSet.manageSpins();


        System.out.println(rotor.getRotorStructure().toString()+"Engine.TheEnigmaEngine.Rotor 1");
        System.out.println(rotor2.getRotorStructure().toString()+"Engine.TheEnigmaEngine.Rotor 2");
        System.out.println("AFTER TWO SPINS");
        rotorsSet.manageSpins();
        System.out.println(rotor.getRotorStructure().toString()+"Engine.TheEnigmaEngine.Rotor 1");
        System.out.println(rotor2.getRotorStructure().toString()+"Engine.TheEnigmaEngine.Rotor 2");

        System.out.println("AFTER 3 SPINS");
        rotorsSet.manageSpins();
        System.out.println(rotor.getRotorStructure().toString()+"Engine.TheEnigmaEngine.Rotor 1");
        System.out.println(rotor2.getRotorStructure().toString()+"Engine.TheEnigmaEngine.Rotor 2");*/
    //}
