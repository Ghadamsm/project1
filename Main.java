import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Main {

    //  اللعبه ستاتيك عشان تقلل لي استخدام الذاكرة
             static char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};


            public static void main(String[] args) {

                Scanner scanner = new Scanner(System.in);
                Random random = new Random();

                System.out.println("Welcome to Tic Tac Toe!");



                //  خيارات اللعب اذا مرة ولا 3 لعبات
               // اكسبشن عشان لو اختار احرف بدال الارقام و رقم المكان الي يبيه

              int option = 0;


              try {


                do
                {
                System.out.println("Choose an option:");
                System.out.println("1. Play one round");
                System.out.println("2. Play 3 rounds and determine the winner");
                option = scanner.nextInt();

                  switch (option) {
                    case 1:
                        playRound(scanner, random);
                        break;
                    case 2:
                        playMultipleRounds(scanner, random);
                        break;

                      //  اذا اختار غير 1 ولا 2
                    default:
                        System.out.println("Invalid option. Please choose again."); }
                  }
                while(option !=1&&option !=2);
                    scanner.close();

        } catch (InputMismatchException e){
                  System.out.println("Invalid option. Please choose again.");
              }

            }



    // اختار يلعب راوند واحد
        private static void playRound (Scanner scanner, Random random){

            //  انادي البورد حقت اللعب و قبل اللعب
            initializeBoard();
            displayBoard();

            do {
                playerMove(scanner );

                //  هنا اذا كان اليوزر الفائز
                if (checkWin('X')) {
                    System.out.println("Congratulations! You win!");
                    return;
                }
                if (isBoardFull()) {
                    System.out.println("It's a draw!");
                    return;
                }


                //  اذا الكمبيوتر فاز و كذلك اختيار الكمبيوتر
                computerMove(random );
                displayBoard();

                if (checkWin('O')) {
                    System.out.println("Computer wins! Better luck next time.");
                    return;
                }

            } while (true);
        }



    // اختار لعب 3 راوندات
        private static void playMultipleRounds (Scanner scanner, Random random ){
            int roundsToPlay = 3;
            int playerWins = 0;
            int computerWins = 0;


            //  يطلع لي عدد الفوزات كل واحد كم فاز

            for (int i = 0; i < roundsToPlay; i++) {
                playRound(scanner, random);


                if (checkWin('X')) {
                    playerWins++;
                } else if (checkWin('O')) {
                    computerWins++;
                }
            }

            System.out.println("\nGame Over!");
            System.out.println("Player Wins: " + playerWins);
            System.out.println("Computer Wins: " + computerWins);


            //  تحديد الفائز
            if (playerWins > computerWins) {
                System.out.println("Congratulations! You are the overall winner!");
            } else if (computerWins > playerWins) {
                System.out.println("Computer is the overall winner. Better luck next time.");
            } else {
                System.out.println("It's a tie! No overall winner.");
            }
        }


    //        عرض اللعبة في البداية و كذلك بوقت اللعب
        private static void initializeBoard () {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    board[i][j] = ' ';
                }
            }
        }



        private static void displayBoard () {

            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " | ");
                }
                System.out.println();
            }
        }



    //  لعب اليوزر
        private static void playerMove (Scanner scanner){
            int row, col;
            do {

                //  يحدد المكان الي يبيه
                System.out.println("Enter your move (row and column, e.g., 1 2): ");
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;

                //  اذا كان المكان كويس مو مأخوذ نحطه في مكانه عادي
            } while (!isValidMove(row, col));

            board[row][col] = 'X';
            displayBoard();
        }



    //  الراندوم للكمبيوتر
        private static void computerMove (Random random ){
            int row, col;
            do {
                row = random.nextInt(3);
                col = random.nextInt(3);
            } while (!isValidMove(row, col));

            System.out.println("Computer chooses row " + (row + 1) + " and column " + (col + 1));
            board[row][col] = 'O';
        }



    //  يتحقق لي من المكان الي اختاروه
        private static boolean isValidMove ( int row, int col ){
            return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
        }



    //  تحديد الفائز من rows, columns
        private static boolean checkWin ( char symbol  ){

            return  (board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
                    (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
                    (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||
                    (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
                    (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
                    (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||
                    (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                    (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
        }



        private static boolean isBoardFull () {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        return false;
                    }
                }
            }
            return true;
        }




        }
