import threading
from concurrent.futures import ProcessPoolExecutor
import os

def vegetable_chopper(vegetable_id):
    pid = os.getpid()
    print(pid,"chopped vegetable",vegetable_id)

if __name__ =='__main__':
    
    with ProcessPoolExecutor(max_workers=5) as pool:
        for vegetable in range(100):
            pool.submit(vegetable_chopper, vegetable)    
    # ProcessPoolExecutor shutdown() will freeup resources
    # You can't submit any more tasks after calling shutdown
    # pool.shutdown(wait=True) wai parameter is set to true by default
    # which blocks untill all tasks are done
