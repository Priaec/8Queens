public class Qp{

   
   private boolean tried[][];
   private int cc;
   private int cr;
   private int qp[];
   private int board[][];
  
/*-----------------------------------------------*/  
   public Qp()
   {
      cc = 0;
      tried = new boolean[8][8];
      qp = new int[8];
           
      //think of setting up a virtual object of an 8 by 8 board with all zeros initially
      //lets use a board with 2 dimensions to place the queens on, then convert to a qp array
      for (int j = 0; j < 8; j++)
      {
         for(int k = 0; k < 8; k++)
         {
            tried[j][k] = false;                                      //table [row][column]
         }  
      }
   }
/*-----------------------------------------------*/  
   public int CC()
   {
      return cc;
   }
   
   public int CR()
   {
      return cr;
   }
   
   public boolean getTriedPosition(int x, int y){
      return tried[x][y];
   }
   
   public void placeQueen(int x, int y){
       qp[cc] = cr;  
   }
   
   public boolean solve()
   {
      int N = 8;
      
      while(cc < N)
     {
     //find untried position for cc (path to find one is down the rows until one is a match) if there is
     //no place available, then must backtrack completely
         cr = find_untriedRow(); //our current row position
         placeQueen(cr,cc);
         if(cr == -1)
         {//backtrack
            for(int j = 0; j < 8; j++)
            {
               tried[j][cc] = false; 
            }
            qp[cc] = 0;
            cc--;    
            tried[getRow(cc)][cc] = true;    
           continue;
         }
         //once untried postion found, then see if there are any conflicts. 
        else
         {
            if(findConflicts(cc) == true)
            {
             tried[getRow(cc)][cc] = true;
             cr++;
             continue;
            }
            if(findConflicts(cc) == false)
            {
               cc++;
            }
         }
     }
      if (cc >= N)
         return true;
      else;
      return false;
   }
  
   public int find_untriedRow(){
       for (int j = 0; j < 8; j++)
       {
         if(getTriedPosition(j , CC()) == true)
             continue;
         if (getTriedPosition(j , CC()) == false)
         {
              return j;
         } 
       }
      return -1;
   }
   
 
   public boolean check(int ccol)
   {
         if(ccol == 0)
            return false;
         
         //row and column condition combined
         for(int k = 0; k < ccol; k++)
         {
            if(getRow(ccol) == getRow(k))
              return true;   
         }  
         
         //diag conditions for each direction: 
         //iffy on this condition still     
         for (int j = 1; j <= ccol; j++)
         {
            if(getRow(ccol - j) == getRow(ccol) - j)
            {
               return true;
            }
            
            if(getRow(ccol - j) == getRow(ccol) + j)
            {
               return true;
            }
         }
    //No conflicts? 
    return false;
   }
   public boolean findConflicts(int y)
   {
      int n = this.qp.length;
      int j = 0;
         if (n == y)
             return true;
         if(check(y) == true)
             return true;
      

     return false;
   }
     
   public int getRow(int y)
   {
    if(y < 0 || y > 7)
      {
       return -1;
      }
      return qp[y];
  
   } 
 
   public void printQueenPosition()
   {
      System.out.println("QueenPositions:");
      for(int j = 0; j < 8; j++)
      {
         System.out.print("[" + qp[j] + "]");
      }
      System.out.println("\nColumn:");
      for(int j = 0; j < 8; j++)
      {
         System.out.print("[" + j + "]");
      }
   }
/*-----------------------------------------------*/  
   public static void main (String[] args)
   { 
     Qp board1 = new Qp(); 
      boolean solution = board1.solve(); 
         if(solution == false)
            System.out.println("No solutions were found for the given 8 by 8 Board");
         else{   
            board1.printQueenPosition();
         }
   } 

}


