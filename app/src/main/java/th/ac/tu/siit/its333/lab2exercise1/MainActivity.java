package th.ac.tu.siit.its333.lab2exercise1;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends ActionBarActivity {
    // expr = the current string to be calculated
    StringBuffer expr;
    int memo =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expr = new StringBuffer();
        updateExprDisplay();
    }
    public void updateExprDisplay() {
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(expr.toString());
    }
    public void updateAnsDisplay(String s) {
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(s);
    }
    public void recalculate() {
        //Calculate the expression and display the output
        //Split expr into numbers and operators
        //e.g. 123+45/3 --> ["123", "+", "45", "/", "3"]
        //reference: http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
        String e = expr.toString();
        String[] tokens = e.split("((?<=\\+)|(?=\\+))|((?<=\\-)|(?=\\-))|((?<=\\*)|(?=\\*))|((?<=/)|(?=/))");
        int i = tokens.length;
        String op = "";
        int num = 0;
        int x;
        num = Integer.parseInt(tokens[0]);
        updateAnsDisplay(Integer.toString(num));
        for(x=1;x<i;x++){
            if(x%2==1)
            {
                op = tokens[x];
            }
            else
            {
                if(op.equals("+"))
                {
                    num = num + Integer.parseInt(tokens[x]);
                }
                else if(op.equals("-"))
                {
                    num = num - Integer.parseInt(tokens[x]);
                }
                else if(op.equals("*"))
                {
                    num = num * Integer.parseInt(tokens[x]);
                }
                else if(op.equals("/"))
                {
                    num = num / Integer.parseInt(tokens[x]);
                }
                updateAnsDisplay(Integer.toString(num));
            }
        }
    }
    public void digitClicked(View v) {
        //d = the label of the digit button
        String d = ((TextView)v).getText().toString();
        //append the clicked digit to expr
        expr.append(d);
        //update tvExpr
        updateExprDisplay();
        //calculate the result if possible and update tvAns
        recalculate();
    }
    public void operatorClicked(View v) {
        String d = ((TextView)v).getText().toString();
        String e = expr.toString();
        if(!(e.endsWith("+")||e.endsWith("-")||e.endsWith("*")||e.endsWith("/")||e.isEmpty()))
        {
            expr.append(d);
        }
        else{}
        updateExprDisplay();
        //IF the last character in expr is not an operator and expr is not "",
        //THEN append the clicked operator and updateExprDisplay,
        //ELSE do nothing
    }
    public void ACClicked(View v) {
        //Clear expr and updateExprDisplay
        expr = new StringBuffer();
        updateExprDisplay();
        //Display a toast that the value is cleared
        Toast t = Toast.makeText(this.getApplicationContext(),
                "All cleared", Toast.LENGTH_SHORT);
        t.show();
    }
    public void BSClicked(View v) {
        //Remove the last character from expr, and updateExprDisplay
        if (expr.length() > 0) {
            expr.deleteCharAt(expr.length()-1);
            updateExprDisplay();
        }
    }
    public void EQClicked(View v) {
        //Remove the last character from expr, and updateExprDisplay
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        expr = new StringBuffer();
        expr.append(tvAns.getText().toString());
        updateExprDisplay();
    }
    public void MClicked(View v) {
        //Remove the last character from expr, and updateExprDisplay
        String d = ((TextView)v).getText().toString();
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        if(d.equals("M+"))
        {
            memo = memo + Integer.parseInt(tvAns.getText().toString());
            Toast t = Toast.makeText(this.getApplicationContext(),
                    "memo ="+memo, Toast.LENGTH_SHORT);
            t.show();
        }
        if(d.equals("M-"))
        {
            memo = memo - Integer.parseInt(tvAns.getText().toString());
            Toast t = Toast.makeText(this.getApplicationContext(),
                    "memo ="+memo, Toast.LENGTH_SHORT);
            t.show();
        }
        if(d.equals("MR"))
        {
            expr = new StringBuffer();
            expr.append(memo);
            updateExprDisplay();
            updateAnsDisplay(memo+"");
            Toast t = Toast.makeText(this.getApplicationContext(),
                    "memo ="+memo, Toast.LENGTH_SHORT);
            t.show();
        }
        if(d.equals("MC"))
        {
            memo = 0;
            Toast t = Toast.makeText(this.getApplicationContext(),
                    "memo ="+memo, Toast.LENGTH_SHORT);
            t.show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}