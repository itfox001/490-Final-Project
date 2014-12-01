package com.cis490.ianharrison.pokedex490;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.List;


public class EntryActivity extends Activity {
    String passed_Name;
    String passed_DexID;
    String passed_Species;
    String passed_Height;
    String passed_Weight;
    String passed_Description;
    int passed_Dex_Entry = 0;
    ParseObject pokemon = new ParseObject("PokemonData");
    String fetchedName;
    int size = 777;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);


        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("PokemonData");
        query.whereEqualTo("ID", passed_Dex_Entry);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> pokemonList, com.parse.ParseException e) {
                if (e == null) {
                Log.d("pokemonName", "Retrieved " + pokemonList.size() + " entries");
                    pokemon = pokemonList.get(0);
                    if(pokemonList != null) {
                        fetchedName = pokemonList.get(0).getString("pokemonName");
                    }
                    size = pokemonList.size();
                }
                else {
                Log.d("score", "Error: " + e.getMessage());
                }
            }
        });*/
        createEntryView();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry, menu);
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

    public void createEntryView()
    {
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            //passed_Dex_Entry = extras.getInt("dex_Entry");
            passed_Name = extras.getString("dex_Entry_Name");
            passed_DexID = extras.getString("dex_Entry_DexID");
            passed_Species = extras.getString("dex_Entry_Species");
            passed_Height = extras.getString("dex_Entry_Height");
            passed_Weight = extras.getString("dex_Entry_Weight");
            passed_Description = extras.getString("dex_Entry_Description");

        }
        Toast.makeText(this,"stuff!"+" "+passed_Name + " " + passed_Dex_Entry+ " "
                + passed_Species + " " + passed_Height + " " + passed_Weight,Toast.LENGTH_LONG).show();
        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("PokemonData");
        query.whereEqualTo("ID", passed_Dex_Entry);
        query.findInBackground();*/
    }

}
