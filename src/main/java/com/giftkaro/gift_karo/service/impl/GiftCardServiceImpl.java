package com.giftkaro.gift_karo.service.impl;

import com.giftkaro.gift_karo.dto.GiftCardDTO;
import com.giftkaro.gift_karo.entity.GiftCard;
import com.giftkaro.gift_karo.entity.Restaurant;
import com.giftkaro.gift_karo.entity.User;
import com.giftkaro.gift_karo.exception.ResourceNotFoundException;
import com.giftkaro.gift_karo.repository.GiftCardRepository;
import com.giftkaro.gift_karo.repository.RestaurantRepository;
import com.giftkaro.gift_karo.repository.UserRepository;
import com.giftkaro.gift_karo.service.GiftCardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GiftCardServiceImpl implements GiftCardService {

    private final GiftCardRepository giftCardRepository;
    private final RestaurantRepository restaurantRepository;

    public GiftCardServiceImpl(GiftCardRepository giftCardRepository, RestaurantRepository restaurantRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.giftCardRepository = giftCardRepository;
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    private final ModelMapper modelMapper;
    private  final UserRepository userRepository;

    @Override
    public GiftCardDTO addGiftCard(GiftCardDTO giftCardDTO) {
        // Find the restaurant
        Restaurant restaurant = restaurantRepository.findById(giftCardDTO.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        // Convert DTO to entity
        GiftCard giftCard = modelMapper.map(giftCardDTO, GiftCard.class);
        giftCard.setRestaurant(restaurant);

        // Save gift card
        GiftCard savedGiftCard = giftCardRepository.save(giftCard);

        // Convert back to DTO
        return modelMapper.map(savedGiftCard, GiftCardDTO.class);
    }

    @Override
    public List<GiftCardDTO> getGiftCardsByRestaurant(Long restaurantId) {
        List<GiftCard> giftCards = giftCardRepository.findByRestaurantId(restaurantId);
        return giftCards.stream()
                .map(giftCard -> modelMapper.map(giftCard, GiftCardDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GiftCardDTO getGiftCardById(Long id) {
        GiftCard giftCard = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gift card not found"));
        return modelMapper.map(giftCard, GiftCardDTO.class);
    }

    // Update a gift card by ID
    @Override
    public GiftCardDTO updateGiftCard(Long id, GiftCardDTO giftCardDTO) {
        // Find the existing gift card
        GiftCard existingGiftCard = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gift card not found"));

        // Find the restaurant associated with the gift card
        Restaurant restaurant = restaurantRepository.findById(giftCardDTO.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        // Update the fields of the existing gift card
        existingGiftCard.setName(giftCardDTO.getName());
        existingGiftCard.setAmount(giftCardDTO.getAmount());
        existingGiftCard.setExpirationDate(giftCardDTO.getExpirationDate());
        existingGiftCard.setRestaurant(restaurant); // Update the restaurant association

        // Save the updated gift card
        GiftCard updatedGiftCard = giftCardRepository.save(existingGiftCard);

        // Convert back to DTO
        return modelMapper.map(updatedGiftCard, GiftCardDTO.class);
    }

    // Delete a gift card by ID
    @Override
    public void deleteGiftCard(Long id) {
        GiftCard giftCard = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gift card not found"));

        // Delete the gift card
        giftCardRepository.delete(giftCard);
    }

    public void shareGiftCard(Long senderId, Long recipientId, Long giftCardId, BigDecimal shareAmount) {
        // Fetch the sender, recipient, and gift card from the repositories
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new ResourceNotFoundException("Sender not found"));
        User recipient = userRepository.findById(recipientId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipient not found"));
        GiftCard giftCard = giftCardRepository.findById(giftCardId)
                .orElseThrow(() -> new ResourceNotFoundException("Gift card not found"));

        // Ensure that the gift card has enough remaining balance
        if (giftCard.getRemainingBalance().compareTo(shareAmount) < 0) {
            throw new IllegalArgumentException("Gift card does not have enough balance");
        }

        // Update the gift card's remaining balance
        giftCard.setRemainingBalance(giftCard.getRemainingBalance().subtract(shareAmount));
        giftCardRepository.save(giftCard);

        // Update the recipient's wallet balance
        recipient.setWalletBalance(recipient.getWalletBalance().add(shareAmount));
        userRepository.save(recipient);

        // Log the transaction (optional)
        System.out.println("Shared " + shareAmount + " from gift card " + giftCardId
                + " from user " + senderId + " to user " + recipientId);
    }
}
