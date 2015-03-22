package mcs.gymapp.view.swipe;


import mcs.gymapp.R;
import mcs.gymapp.fragment.dataAdapter.EserAdapter;
import mcs.gymapp.fragment.dataAdapter.EserItem;
import mcs.gymapp.fragment.dataAdapter.GruppoAdapter;

public class BaseSwipeListViewListener implements SwipeListViewListener {

    private GruppoAdapter grpAdap;
    private GruppoAdapter.GrpViewHolder hold;
    private SwipeListView swpList;


    public BaseSwipeListViewListener(GruppoAdapter grpAdap, GruppoAdapter.GrpViewHolder hold, SwipeListView list) {
        this.hold = hold;
        this.grpAdap = grpAdap;
        this.swpList = list;
    }

    @Override
    public void onOpened(int position, boolean toRight) {
        EserItem item = (EserItem) swpList.getAdapter().getItem(position);
        ((EserAdapter.EserViewHolder) item.getHolder() ).countDown(this, position);
    }

    public void autoClose(int pos){
        swpList.closeAnimate(pos);
    }



    @Override
    public void onClosed(int position, boolean fromRight) {
        EserItem item = (EserItem) swpList.getAdapter().getItem(position);
        if (item.getnSer() > 0) {
            if (item.getnSer() == 1) {
                //swpList.addInvalidItem(position);
                ((EserAdapter) swpList.getAdapter()).addInvalidItem(position);
            }
            item.scalaSerie();
            ((EserAdapter) swpList.getAdapter()).notifyDataSetChanged();
        }
    }

    @Override
    public void onListChanged() {
    }

    @Override
    public void onMove(int position, float x) {
    }

    @Override
    public void onStartOpen(int position, int action, boolean right) {

    }

    @Override
    public void onStartClose(int position, boolean right) {
    }

    @Override
    public void onClickFrontView(int position) {
    }

    @Override
    public void onClickBackView(int position) {
    }

    @Override
    public void onDismiss(int[] reverseSortedPositions) {
        for (int position : reverseSortedPositions) {
            grpAdap.getData().remove(position);
        }
        grpAdap.notifyDataSetChanged();
    }

    @Override
    public int onChangeSwipeMode(int position) {
        return SwipeListView.SWIPE_MODE_DEFAULT;
    }

    @Override
    public void onChoiceChanged(int position, boolean selected) {
    }

    @Override
    public void onChoiceStarted() {
    }

    @Override
    public void onChoiceEnded() {
    }

    @Override
    public void onFirstListItem() {
    }

    @Override
    public void onLastListItem() {
    }
}