import React, { useEffect } from "react";
import TaskCard from "../TaskCard/TaskCard";
import {useDispatch, useSelector} from "react-redux"
import { fetchTasks } from "../../../ReduxToolkit/TaskSlice";

const TaskList = () => {
  const dispatch = useDispatch();
  const {task} = useSelector(store => store);

  useEffect(() => {
    dispatch(fetchTasks({}))
  }, []);

  console.log("task", task);

  return (
    <div className="space-y-5 w-[67vw]">
      <div className="space-y-3">
        {[1, 1, 1, 1].map((item) => (
          <TaskCard />
        ))}
      </div>
    </div>
  );
};

export default TaskList;
