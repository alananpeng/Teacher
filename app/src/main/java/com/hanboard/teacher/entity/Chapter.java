package com.hanboard.teacher.entity;

import com.hanboard.teacher.entity.tree.TreeNodeCId;
import com.hanboard.teacher.entity.tree.TreeNodeId;
import com.hanboard.teacher.entity.tree.TreeNodeLabel;
import com.hanboard.teacher.entity.tree.TreeNodePid;

public class Chapter{
	public String getId() {

		return id;
	}

	@TreeNodeCId
	private String id;
	@TreeNodeId
	public int _id;
	@TreeNodePid
	public int parentId;
	@TreeNodeLabel
	public String name;
	public String parentid;

	public Chapter(int _id, int parentId, String name,String id,String parentid)
	{
		super();
		this._id = _id;
		this.parentId = parentId;
		this.name = name;
		this.id = id;
		this.parentid = parentid;
	}

}
