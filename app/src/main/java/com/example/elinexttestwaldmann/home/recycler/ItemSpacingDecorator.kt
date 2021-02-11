package com.example.elinexttestwaldmann.home.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemSpacingDecorator (private val spacing: Int) : RecyclerView.ItemDecoration()
{
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    )
    {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = spacing
        outRect.bottom = spacing
        outRect.left = spacing
        outRect.right = spacing
    }
}