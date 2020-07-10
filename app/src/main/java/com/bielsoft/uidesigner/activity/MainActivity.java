package com.bielsoft.uidesigner.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import com.bielsoft.uidesigner.R;
import com.bielsoft.uidesigner.adapter.WidgetsAdapter;
import com.bielsoft.uidesigner.util.Const;
import com.bielsoft.uidesigner.util.DesigerUtil;
import com.bielsoft.uidesigner.view.DesignerLayout;

public class MainActivity extends BaseActivity 
{
    public static DrawerLayout drawer;
    private Toolbar toolbar;
    private DesignerLayout designerLayout;
    private LinearLayout navView;
    private RecyclerView recyclerWidgets;

    private MenuItem actionEditMode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
        initialize();
    }

    @Override
    public void findViews()
    {
        super.findViews();
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        designerLayout = (DesignerLayout) findViewById(R.id.designer_layout);
        navView = (LinearLayout) findViewById(R.id.nav_view);
        recyclerWidgets = (RecyclerView) findViewById(R.id.recycler_widgets);
    }

    @Override
    public void initialize()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    onBackPressed();
                }
            });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        designerLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    drawer.openDrawer(GravityCompat.START); 
                }
            });

        recyclerWidgets.setHasFixedSize(true);
        recyclerWidgets.setLayoutManager(new LinearLayoutManager(this));
        recyclerWidgets.setAdapter(new WidgetsAdapter(designerLayout, Const.getWidgetsGroup()));

        super.initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        actionEditMode = menu.findItem(R.id.action_edit_modo);
        modifyActionEditMode();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_edit_modo :
                modifyActionEditMode();
                return true;
            case R.id.action_settings :
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void modifyActionEditMode()
    {
        if (actionEditMode == null)
            return;
        if (designerLayout.isEditMode())
        {
            designerLayout.toggleEditMode();
            actionEditMode.setIcon(R.drawable.ic_visibility_white);
            actionEditMode.setTitle("View");
        }
        else
        {
            designerLayout.toggleEditMode();
            actionEditMode.setIcon(R.drawable.ic_edit_white);
            actionEditMode.setTitle("Edit");
        }
        DesigerUtil.setTint(actionEditMode.getIcon(), getResources().getColor(R.color.colorTint));
    }

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
	}
}
