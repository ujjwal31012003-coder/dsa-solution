class Solution {
    public List<Integer> getRow(int rowIndex) {
     List<List<Integer>> answer = new ArrayList<>();
     List<Integer> firstRow = new ArrayList<>();
     firstRow.add(1);
     answer.add(firstRow);
     for(int i = 1; i <= rowIndex; i++){
        List<Integer> previous = answer.get(i -1);
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for(int j = 1; j <  previous.size();j++) {
            row.add(previous.get(j-1) + previous.get(j));
        }
        row.add(1);

        answer.add(row);
        }
    
     
     return answer.get(rowIndex); 
    }
}