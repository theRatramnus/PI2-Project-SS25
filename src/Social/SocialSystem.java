package Social;

import LifeAndDeath.EntityManager;

import java.util.List;
import java.util.function.Consumer;

/**
 * simulates social interactions
 * @param <T> the entities' class
 */

public class SocialSystem<T extends SocialEntity> implements EntityManager<T> {

   /**
    * the entities that make up the system
    */
   List<T> entities;

   public SocialSystem(List<T> entities) {
      this.entities = entities;
   }

   /**
    * calculates the interactions between the entities
    */
   public void triggerInteractions() {
      for (int i = 0; i < entities.size(); i++) {
         T interactor = entities.get(i);
         for (int j = 0; j < entities.size(); j++) {
            if (i == j) {
               continue;
            }
            var interactee = entities.get(j);
            if (interactor.getPosition().distanceTo(interactee.getPosition()) < interactor.getInteractionRadius()) {
               interactor.interactWith(interactee);
            }
         }
      }
   }


   @Override
   public void addEntity(T e) {
      entities.add(e);
   }

   @Override
   public void removeEntity(T e) {
      entities.remove(e);
   }



   @Override
   public void forEachEntity(Consumer<T> action) {
      entities.forEach(action);
   }

}
