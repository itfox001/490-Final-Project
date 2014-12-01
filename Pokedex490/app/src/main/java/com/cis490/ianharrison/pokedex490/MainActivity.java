package com.cis490.ianharrison.pokedex490;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
ListView lv;
    String[] days = {"Sunday","Monday","Tuesday"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(this, "MLDl6ZuzUJl3S1MQsi0wXPOGHvfV61sAQUpFlOlJ", "SsGLHDfnRi1erCYyaX2UrCegGoOvFkxBZZ8JSymx");
        //ParseObject testObject = new ParseObject("TestObject");
        //testObject.put("foo", "bars");
        //testObject.saveInBackground();
        lv = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,this.getResources().getStringArray(R.array.pokemon_names));
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
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

    String name;
    String dexID;
    String species;
    String height;
    String weight;
    String description;
    ParseFile picture;
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("PokemonData");
        query.whereEqualTo("ID", i+1);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    //Log.d("score", "Retrieved " + parseObjects.size() + " scores");
                    name = parseObjects.get(0).getString("pokemonName");
                    dexID = parseObjects.get(0).getString("DexID");
                    species = parseObjects.get(0).getString("species");
                    height = parseObjects.get(0).getString("height");
                    weight = parseObjects.get(0).getString("weight");
                    description = parseObjects.get(0).getString("description");
                    picture = parseObjects.get(0).getParseFile("pictureURL");
                } else {
                    //Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        Intent intent = new Intent(getApplicationContext(),EntryActivity.class);
        intent.putExtra("dex_Entry",dexID);
        intent.putExtra("dex_Entry_Name",name);
        intent.putExtra("dex_Entry_Species",species);
        intent.putExtra("dex_Entry_Height",height);
        intent.putExtra("dex_Entry_Weight",weight);
        intent.putExtra("dex_Entry_Description",description);
        startActivity(intent);
    }
}
