import threading
from concurrent.futures import ThreadPoolExecutor
import os


def vegetable_chopper(vegetable_id):
    pid = os.getpid()
    name = threading.current_thread().getName()
    print(pid,name,"chopped vegetable",vegetable_id)

if __name__ =='__main__':
    pool = ThreadPoolExecutor(max_workers=5)
    for vegetable in range(100):
       pool.submit(vegetable_chopper, vegetable)
    pool.shutdown()
    # ThreadPoolExecutor shutdown() will freeup resources
    # You can't submit any more tasks after calling shutdown
    # pool.shutdown(wait=True) wai parameter is set to true by default
    # which blocks untill all tasks are done
